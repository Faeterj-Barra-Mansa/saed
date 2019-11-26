package br.gov.rj.faetec.saed.disciplina.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.rj.faetec.saed.disciplina.filter.DisciplinaFilter;
import br.gov.rj.faetec.saed.disciplina.model.Disciplina;

public interface DisciplinasQueries {

	Page<Disciplina> filtrar(DisciplinaFilter filter, Pageable page);
}
