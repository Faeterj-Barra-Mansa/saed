package br.gov.rj.faetec.saed.curso.controller;

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

import br.gov.rj.faetec.saed.curso.filter.CursoFilter;
import br.gov.rj.faetec.saed.curso.model.Curso;
import br.gov.rj.faetec.saed.curso.repository.Cursos;
import br.gov.rj.faetec.saed.curso.service.CursoService;
import br.gov.rj.faetec.saed.curso.service.exception.CursoJaCadastradoException;
import br.gov.rj.faetec.saed.usuario.service.exception.ImpossivelExcluirEntidadeException;
import br.gov.rj.faetec.saed.webapp.controller.page.PageWrapper;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private Cursos cursos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Curso curso) {
		ModelAndView mv = new ModelAndView("cursos/CadastroCurso");
		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(curso);
		}
		
		try {
			cursoService.salvar(curso);
		}catch (CursoJaCadastradoException e) {
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return novo(curso);
		}
		
		attributes.addFlashAttribute("mensagem", "Curso salvo com sucesso!");
		return new ModelAndView("redirect:/cursos");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(CursoFilter cursoFilter, BindingResult result, 
			@PageableDefault(size =5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cursos/PesquisaCurso");
	
		PageWrapper<Curso> paginaWrapper = new PageWrapper<>(cursos.filtrar(cursoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);

		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Curso curso) {
		try {
			cursoService.excluir(curso);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Curso curso) {
		ModelAndView mv = novo(curso);
		mv.addObject(curso);
		return mv;
		
	}
	
}
