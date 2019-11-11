package br.gov.rj.faetec.saed.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.rj.faetec.saed.usuario.model.Grupo;

public interface Grupos extends JpaRepository<Grupo, Long> {

}
