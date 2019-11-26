package br.gov.rj.faetec.saed.disciplina.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.rj.faetec.saed.disciplina.model.Disciplina;
import br.gov.rj.faetec.saed.disciplina.repository.Disciplinas;
import br.gov.rj.faetec.saed.disciplina.service.exception.CodigoDisciplinaJaCadastradoException;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class DisciplinaService {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Transactional 
	public void salvar(Disciplina disciplina) throws CodigoDisciplinaJaCadastradoException {
		Optional<Disciplina> disciplinaOptional = disciplinas.findByCodigo(disciplina.getCodigo());
		if (disciplinaOptional.isPresent() && disciplina.isNovo()) {
			throw new CodigoDisciplinaJaCadastradoException("Codigo de disciplina já cadastrado");
		}
		
		disciplinas.save(disciplina); 
		
	}
	
	@Transactional
	public void excluir(Disciplina disciplina) {
		try {
			disciplinas.delete(disciplina); 
			disciplinas.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar disciplina. Possui referência.");
		}
	}

}
