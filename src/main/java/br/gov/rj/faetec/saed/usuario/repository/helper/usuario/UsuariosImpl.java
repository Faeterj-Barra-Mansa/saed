package br.gov.rj.faetec.saed.usuario.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.rj.faetec.saed.usuario.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", 
				 Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

}
