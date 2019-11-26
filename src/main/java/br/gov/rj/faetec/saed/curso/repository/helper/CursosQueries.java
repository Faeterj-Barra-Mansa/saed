package br.gov.rj.faetec.saed.curso.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.rj.faetec.saed.curso.filter.CursoFilter;
import br.gov.rj.faetec.saed.curso.model.Curso;

public interface CursosQueries {
	
	Page<Curso> filtrar(CursoFilter filter, Pageable page);

}
