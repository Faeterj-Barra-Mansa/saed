package br.gov.rj.faetec.saed.disciplina.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.rj.faetec.saed.disciplina.filter.DisciplinaFilter;
import br.gov.rj.faetec.saed.disciplina.model.Disciplina;
import br.gov.rj.faetec.saed.disciplina.repository.Disciplinas;
import br.gov.rj.faetec.saed.disciplina.service.DisciplinaService;
import br.gov.rj.faetec.saed.disciplina.service.exception.CodigoDisciplinaJaCadastradoException;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;
import br.gov.rj.faetec.saed.webapp.controller.page.PageWrapper;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("disciplinas/CadastroDisciplina");
		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Disciplina disciplina, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(disciplina);
		}
		
		try {
			disciplinaService.salvar(disciplina);
		}catch (CodigoDisciplinaJaCadastradoException e) {
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return novo(disciplina);
		}
		
		attributes.addFlashAttribute("mensagem", "Disciplina salvo com sucesso!");
		return new ModelAndView("redirect:/disciplinas");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(DisciplinaFilter disciplinaFilter, BindingResult result, 
			@PageableDefault(size =5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("disciplinas/PesquisaDisciplina");
	
		PageWrapper<Disciplina> paginaWrapper = new PageWrapper<>(disciplinas.filtrar(disciplinaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Disciplina disciplina) {
		try {
			disciplinaService.excluir(disciplina);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Disciplina disciplina) {
		ModelAndView mv = novo(disciplina);
		mv.addObject(disciplina);
		return mv;
		
	}
}
