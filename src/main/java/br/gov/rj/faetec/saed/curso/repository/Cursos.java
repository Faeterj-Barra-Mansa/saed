package br.gov.rj.faetec.saed.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.saed.curso.model.Curso;
import br.gov.rj.faetec.saed.curso.repository.helper.CursosQueries;

@Repository
public interface Cursos extends JpaRepository<Curso, Long>, CursosQueries{

	Optional<Curso> findByNome(String nome);
}