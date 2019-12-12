package br.gov.rj.faetec.saed.turma.repository.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.gov.rj.faetec.saed.turma.filter.TurmaFilter;
import br.gov.rj.faetec.saed.turma.model.Turma;
import br.gov.rj.faetec.saed.webapp.paginacao.PaginacaoUtil;

public class TurmasImpl implements TurmasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Turma> filtrar(TurmaFilter filtro, Pageable pageable){
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Turma.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}	
		
	
	
	private Long total(TurmaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Turma.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return(Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(TurmaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getId())) {
				criteria.add(Restrictions.ilike("id", filtro.getId()));
			}
		}	

	}	
}