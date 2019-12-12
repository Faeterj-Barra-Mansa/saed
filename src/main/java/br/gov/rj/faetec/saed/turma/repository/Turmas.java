package br.gov.rj.faetec.saed.turma.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rj.faetec.saed.turma.model.Turma;
import br.gov.rj.faetec.saed.turma.repository.helper.TurmasQueries;

@Repository
public interface Turmas extends JpaRepository <Turma, Long>, TurmasQueries {
	
	Optional<Turmas> findById(Long id);

}
