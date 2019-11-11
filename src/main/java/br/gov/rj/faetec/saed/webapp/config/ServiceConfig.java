package br.gov.rj.faetec.saed.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.gov.rj.faetec.saed.usuario.service.CadastroUsuarioService;

@Configuration // Trata-se de uma classe de configuração
@ComponentScan(basePackageClasses = {CadastroUsuarioService.class}) // Onde deverá ser procurado 
public class ServiceConfig {                                      // os componentes desta classe

}
