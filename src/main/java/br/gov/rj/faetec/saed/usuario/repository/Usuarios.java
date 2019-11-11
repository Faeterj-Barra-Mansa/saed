package br.gov.rj.faetec.saed.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.rj.faetec.saed.usuario.model.Usuario;
import br.gov.rj.faetec.saed.usuario.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmail(String email);

}
