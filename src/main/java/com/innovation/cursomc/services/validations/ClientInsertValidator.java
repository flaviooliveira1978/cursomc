package com.innovation.cursomc.services.validations;

	import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.innovation.cursomc.domain.enums.TipoCliente;
import com.innovation.cursomc.dto.ClienteNewDTO;
import com.innovation.cursomc.resources.exception.FieldMessage;
import com.innovation.cursomc.services.validations.utils.DocumentValidator;
	
	public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	 @Override
	 public void initialize(ClientInsert ann) {
	 }
	 
	 
	 @Override
	 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	 List<FieldMessage> list = new ArrayList<>();

	 if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !DocumentValidator.isValidCPF(objDto.getCpfOuCnpj())) {
		 list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
	 }
	 if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !DocumentValidator.isValidCNPJ(objDto.getCpfOuCnpj())) {
		 list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
	 }

	 for (FieldMessage e : list) {
	 context.disableDefaultConstraintViolation();
	 context.buildConstraintViolationWithTemplate(e.getMessage())
	 .addPropertyNode(e.getFieldName()).addConstraintViolation();
	 }
	 return list.isEmpty();
	 }
	}
