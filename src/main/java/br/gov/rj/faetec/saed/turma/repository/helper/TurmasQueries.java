package br.gov.rj.faetec.saed.turma.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.rj.faetec.saed.turma.filter.TurmaFilter;
import br.gov.rj.faetec.saed.turma.model.Turma;

public interface TurmasQueries {
	
	Page<Turma> filtrar(TurmaFilter filter, Pageable page);

}
