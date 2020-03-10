package com.mastertech.cartoesapp.converter;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.dto.PagamentoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.PagamentoEntity;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;

@Component
public class PagamentoConverter {
	
	public PagamentoEntity convertFromDtoToEntity(PagamentoDTO dto, CartaoEntity cartao) throws CartaoNaoExisteException, CartaoInativoException {
		
		PagamentoEntity entity = new PagamentoEntity();
		
		entity.setCartao(cartao);
		entity.setDecricao(dto.getDescricao());
		entity.setValor(dto.getValor());

		return entity;
	}
	
	public PagamentoDTO convertFromEntityToDto (PagamentoEntity entity) {
		PagamentoDTO dto = new PagamentoDTO();
	
		dto.setCartaoId(entity.getCartao().getId());
		dto.setId(entity.getId());
		dto.setDescricao(entity.getDecricao());
		dto.setValor(entity.getValor());
		
		return dto;
	}
}
