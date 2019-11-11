package br.gov.rj.faetec.saed.webapp.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.gov.rj.faetec.saed.webapp.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.gov.rj.faetec.saed.webapp.thymeleaf.processor.MenuAttributeTagProcessor;
import br.gov.rj.faetec.saed.webapp.thymeleaf.processor.MessageElementTagProcessor;
import br.gov.rj.faetec.saed.webapp.thymeleaf.processor.OrderElementTagProcessor;
import br.gov.rj.faetec.saed.webapp.thymeleaf.processor.PaginationElementTagProcessor;

public class EstoqueDialect extends AbstractProcessorDialect {

	public EstoqueDialect() {
		super("Faetec Estoque", "estoque", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
