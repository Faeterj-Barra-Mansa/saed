package br.gov.rj.faetec.saed.disciplina.repository.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.gov.rj.faetec.saed.disciplina.filter.DisciplinaFilter;
import br.gov.rj.faetec.saed.disciplina.model.Disciplina;
import br.gov.rj.faetec.saed.webapp.paginacao.PaginacaoUtil;


public class DisciplinasImpl implements DisciplinasQueries {

	@PersistenceContext
	private EntityManager manager;
	
//	@Autowired
//	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Disciplina> filtrar(DisciplinaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Disciplina.class);
		PaginacaoUtil paginacaoUtil = new PaginacaoUtil();
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(DisciplinaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Disciplina.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(DisciplinaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			
		}
	}
	
}
