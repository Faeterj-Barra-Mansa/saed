package br.gov.rj.faetec.saed.curso.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.rj.faetec.saed.curso.model.Curso;
import br.gov.rj.faetec.saed.curso.repository.Cursos;
import br.gov.rj.faetec.saed.curso.service.exception.CursoJaCadastradoException;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CursoService {
	
	@Autowired
	private Cursos cursos;
	
	@Transactional 
	public void salvar(Curso curso) throws CursoJaCadastradoException {
		Optional<Curso> cursoOptional = cursos.findByNome(curso.getNome());
		if (cursoOptional.isPresent() && curso.isNovo()) {
			throw new CursoJaCadastradoException("Curso já cadastrado");
		}
		
		cursos.save(curso); 
		
	}
	
	@Transactional
	public void excluir(Curso curso) {
		try {
			cursos.delete(curso); 
			cursos.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar curso. Possui referência.");
		}
	}

}
