package com.innovation.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.innovation.cursomc.domain.Cidade;
import com.innovation.cursomc.domain.Cliente;
import com.innovation.cursomc.domain.Endereco;
import com.innovation.cursomc.domain.enums.TipoCliente;
import com.innovation.cursomc.dto.ClienteDTO;
import com.innovation.cursomc.dto.ClienteNewDTO;
import com.innovation.cursomc.repositories.ClienteRepository;
import com.innovation.cursomc.repositories.EnderecoRepository;
import com.innovation.cursomc.services.exceptions.DataIntegrityException;
import com.innovation.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	
	@Autowired
	private EnderecoRepository endRepo;

		
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	
	}
	
	
	public List<Cliente> findAll() {		
		return repo.findAll();
		 
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		
		return repo.findAll(pageRequest);
		 
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		obj = repo.save(obj);

		endRepo.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		
		Cliente newObj = find(obj.getId());
		updateCliente(newObj,obj);
		
		return repo.save(newObj);
	}
	

	public void delete(Integer id) {
		find(id);
		try {
			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente com Pedidos");
		}
		
		
	}
	

	
	private void updateCliente(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO (ClienteDTO objDto){
		
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	
	public Cliente fromDTO (ClienteNewDTO objDto){
		Cliente cli = new Cliente(null,objDto.getNome(),objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()));
		
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);

		Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cid,cli);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
			
		}
		
		if(objDto.getTelefone3() !=null) {
			cli.getTelefones().add(objDto.getTelefone3());
			
		}
				
				
		return cli;
	}
	
}
