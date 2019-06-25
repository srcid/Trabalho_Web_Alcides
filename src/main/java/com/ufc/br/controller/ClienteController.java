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

import com.ufc.br.model.Cliente;
import com.ufc.br.service.ClienteService;

@RequestMapping("/cliente")
@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/formulario")
	public ModelAndView formularioPessoa() {
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView cadastrarCliente(@Validated Cliente cliente, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("Formulario");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		mv.addObject("mensagem", "Cliente cadastrado com sucesso!");
		
		clienteService.salvar(cliente);
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listarClientes() {
		List<Cliente> clientes = clienteService.listarClientes();
		ModelAndView mv = new ModelAndView("Listar_Clientes");
		mv.addObject("clientes", clientes);
		return mv;
	}
	
	@RequestMapping("/deletar/{id}")
	public ModelAndView deletarCliente(@PathVariable Long id) {
		clienteService.delete(id);
		return new ModelAndView("redirect:/cliente/listar");
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizarCliente(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject("cliente", cliente);
		return mv;
	}
}
