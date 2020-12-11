package com.innovation.cursomc.domain;

import javax.persistence.Entity;

import com.innovation.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private Integer parcelas;
	private static final long serialVersionUID = 1L;
	
	public PagamentoComCartao() {
		
	}


	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer parcelas) {
		super(id, estado, pedido);
		this.parcelas = parcelas;
	}


	public Integer getParcelas() {
		return parcelas;
	}


	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	
	
}
