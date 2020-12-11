package com.innovation.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.cursomc.domain.Cliente;
import com.innovation.cursomc.eexceptions.ObjectNotFoundException;
import com.innovation.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		if (obj ==null) {
			throw new ObjectNotFoundException("Cliente não encontrado. Id: "+ id);

		}
		else { 
		
		/*
		 * return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		 */
		
		return obj.get(); 
		}
	}
}
