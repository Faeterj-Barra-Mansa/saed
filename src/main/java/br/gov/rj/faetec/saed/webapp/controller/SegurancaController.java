package br.gov.rj.faetec.saed.webapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public ModelAndView login(@AuthenticationPrincipal User user) {
		if (user != null) {               // Caso o usuário tente acessar o login e se estiver logado
			return new ModelAndView("redirect:/");  // vai para a página de produtos
		}
		
		return new ModelAndView("Login");
	}
	
}
