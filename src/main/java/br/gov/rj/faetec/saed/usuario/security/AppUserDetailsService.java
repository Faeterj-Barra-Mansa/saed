package br.gov.rj.faetec.saed.usuario.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.rj.faetec.saed.usuario.model.Usuario;
import br.gov.rj.faetec.saed.usuario.repository.Usuarios;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarios.porEmailEAtivo(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new 
				          UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(usuario.getEmail(), usuario.getSenha(), new HashSet<>());
	}	

}