package com.ufc.br.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements GrantedAuthority {
	@Id
	private String roleCliente;
	
	@ManyToMany(mappedBy = "roles")
	private List<Cliente> clientes;

	public String getRoleCliente() {
		return roleCliente;
	}

	public void setRoleCliente(String roleCliente) {
		this.roleCliente = roleCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public String getAuthority() {
		return this.roleCliente;
	}
}
