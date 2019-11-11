package br.gov.rj.faetec.saed.usuario.repository.helper.usuario;

import java.util.Optional;

import br.gov.rj.faetec.saed.usuario.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
}
