package com.innovation.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.innovation.cursomc.domain.Categoria;
import com.innovation.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public Integer id;
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 120 caracteres")
	public String nome;

	@NotEmpty(message="Preenchimento do Email é obrigatório")
	@Email
	private String email;
	
	public ClienteDTO () {
		
	}
	
	public ClienteDTO(Cliente cat) {
		id = cat.getId();
		nome = cat.getNome();
		email = cat.getEmail();	
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
