package br.gov.rj.faetec.saed.disciplina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.saed.disciplina.model.Disciplina;
import br.gov.rj.faetec.saed.disciplina.repository.helper.DisciplinasQueries;

@Repository
public interface Disciplinas extends JpaRepository<Disciplina, Long>,  DisciplinasQueries{

	Optional<Disciplina> findByCodigo(Long codigo);

}
