package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@RequestMapping("/prato")
@Controller
public class PratoController {
	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/formulario")
	public ModelAndView formularioPrato() {
		ModelAndView mv = new ModelAndView("FormularioPratos");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView cadastrarPrato(@Validated Prato prato, BindingResult result,
			@RequestParam(value="imagem") MultipartFile imagem) {
		
		ModelAndView mv = new ModelAndView("FormularioPratos");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		mv.addObject("mensagem", "Prato cadastrado com sucesso!");
		
		pratoService.salvar(prato, imagem);
		
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listarPratos() {
		List<Prato> pratos = pratoService.listarPratos();
		ModelAndView mv = new ModelAndView("Listar_Pratos");
		mv.addObject("pratos", pratos);
		return mv;
	}
	
	@RequestMapping("/deletar/{id}")
	public ModelAndView deletarPrato(@PathVariable Long id) {
		pratoService.delete(id);
		return new ModelAndView("redirect:/prato/listar");
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizarPrato(@PathVariable Long id) {
		Prato prato = pratoService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("FormularioPratos");
		mv.addObject("prato", prato);
		return mv;
	}
}
