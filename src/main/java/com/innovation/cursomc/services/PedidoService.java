package com.innovation.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.cursomc.domain.Pedido;
import com.innovation.cursomc.eexceptions.ObjectNotFoundException;
import com.innovation.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		if (obj ==null) {
			throw new ObjectNotFoundException("Pedido não encontrado. Id: "+ id);

		}
		else { 
		
		/*
		 * return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		 */
		
		return obj.get(); 
		}
	}
}
