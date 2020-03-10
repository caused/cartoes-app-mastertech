package com.mastertech.cartoesapp.pagamento.converter;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.cartao.entity.CartaoEntity;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.pagamento.dto.PagamentoDTO;
import com.mastertech.cartoesapp.pagamento.entity.PagamentoEntity;

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
