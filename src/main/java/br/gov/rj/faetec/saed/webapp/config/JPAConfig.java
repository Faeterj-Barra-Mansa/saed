package br.gov.rj.faetec.saed.webapp.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.gov.rj.faetec.saed.curso.model.Curso;
import br.gov.rj.faetec.saed.curso.repository.Cursos;
import br.gov.rj.faetec.saed.disciplina.model.Disciplina;
import br.gov.rj.faetec.saed.disciplina.repository.Disciplinas;
import br.gov.rj.faetec.saed.usuario.model.Usuario;
import br.gov.rj.faetec.saed.usuario.repository.Usuarios;
import br.gov.rj.faetec.saed.webapp.paginacao.PaginacaoUtil;
import br.gov.rj.faetec.saed.turma.model.Turma;
import br.gov.rj.faetec.saed.turma.repository.Turmas;
@Configuration
@ComponentScan(basePackageClasses = { Usuarios.class, Disciplinas.class,
		Cursos.class, Turmas.class, PaginacaoUtil.class})
// Nos repositórios do JPA está sendo desabilitada a transação default
@EnableJpaRepositories(basePackageClasses = { Usuarios.class, Disciplinas.class,
		Cursos.class, Turmas.class, PaginacaoUtil.class }, enableDefaultTransactions = false) 
//Com a anotação abaixo, a transação no banco de dados, deixa de ser automática. 
//É necessário declarar na classe CadastroProdutoService a notação @Transactional
@EnableTransactionManagement 
public class JPAConfig {

	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/saedDB");
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(false);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Usuario.class.getPackage().getName(),
				Disciplina.class.getPackage().getName(),
				Curso.class.getPackage().getName(),
				Turma.class.getPackage().getName()
				);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
}
