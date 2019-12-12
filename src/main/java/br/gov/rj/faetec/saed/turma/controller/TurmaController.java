package br.gov.rj.faetec.saed.turma.controller;

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

import br.gov.rj.faetec.saed.turma.filter.TurmaFilter;
import br.gov.rj.faetec.saed.turma.model.Turma;
import br.gov.rj.faetec.saed.turma.repository.Turmas;
import br.gov.rj.faetec.saed.turma.service.exception.TurmaJaCadastradoException;
import br.gov.rj.faetec.saed.turma.service.exception.TurmaService;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;
import br.gov.rj.faetec.saed.webapp.controller.page.PageWrapper;

@Controller
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private Turmas turmas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Turma turma) {
		ModelAndView mv = new ModelAndView("turmas/CadastroTurma");
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Turma turma, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(turma);
		}
	
	try {
		turmaService.salvar(turma);
	}catch (TurmaJaCadastradoException e) {
		result.rejectValue("codigo", e.getMessage(), e.getMessage());
		return novo(turma);
	}
	
		attributes.addFlashAttribute("message", "Turma cadastrada com sucesso!");
		return new ModelAndView("redirect:/turmas");

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(TurmaFilter turmaFilter, BindingResult result,
			@PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("turmas/PessquisarTurmas");
		
		PageWrapper<Turma> paginawrapper = new PageWrapper<>(turmas.filtrar(turmaFilter, pageable)
				,httpServletRequest);
		mv.addObject("pagina", paginawrapper);
		
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Turma turma){
		try {
			turmaService.excluir(turma);
		}catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Turma turma) {
		ModelAndView mv = novo(turma);
		mv.addObject(turma);
		return mv;
	}	
	
}