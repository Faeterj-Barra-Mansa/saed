package br.gov.rj.faetec.saed.turma.service.exception;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.rj.faetec.saed.turma.model.Turma;
import br.gov.rj.faetec.saed.turma.repository.Turmas;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class TurmaService {
	
	@Autowired
	private Turmas turmas;
	
	@Transactional
	public void salvar (Turma turma) throws TurmaJaCadastradoException {
		java.util.Optional<Turmas> turmaOptional = turmas.findById(turma.getId());
		if (turmaOptional.isPresent() && turma.isNovo()) {
			throw new TurmaJaCadastradoException("Turma já cadastrada");
		}
		
		turmas.save(turma);
	}
	
	@Transactional
	public void excluir(Turma turma) {
		try {
			turmas.delete(turma);
			turmas.delete(turma);
		}catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar turma. possui referência.");
		}
	}

}
