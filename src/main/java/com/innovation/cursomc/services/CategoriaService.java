package com.innovation.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.cursomc.domain.Categoria;
import com.innovation.cursomc.eexceptions.ObjectNotFoundException;
import com.innovation.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		if (obj ==null) {
			throw new ObjectNotFoundException("Categoria não encontrada. Id: "+ id);

		}
		else { 
		
		/*
		 * return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		 */
		
		return obj.get(); 
		}
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	

}
