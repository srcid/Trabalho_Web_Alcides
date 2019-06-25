package com.ufc.br.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.ClienteRepository;
import com.ufc.br.repository.RoleRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void salvar(Cliente cliente) {
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		Role role = roleRepository.findByRoleCliente("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		cliente.setRoles(roles);
		clienteRepository.save(cliente);
	}
	
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente buscarPorId(Long id) {
		return clienteRepository.getOne(id);
	}
	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
	public Cliente findByEmail(String email) {
		return clienteRepository.findByEmail(email);
	}
}
