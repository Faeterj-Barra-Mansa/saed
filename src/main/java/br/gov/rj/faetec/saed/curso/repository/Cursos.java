package br.gov.rj.faetec.saed.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.rj.faetec.saed.curso.model.Curso;

public interface Cursos extends JpaRepository<Curso, Long>{
	
}