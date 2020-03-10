package com.mastertech.cartoesapp.converter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.dto.PagamentoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.PagamentoEntity;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.repository.CartaoRepository;

@Component
public class PagamentoConverter {

	private CartaoRepository cartaoRepository;
	
	public PagamentoConverter (CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	public PagamentoEntity convertFromDtoToEntity(PagamentoDTO dto) throws CartaoNaoExisteException, CartaoInativoException {
		
		PagamentoEntity entity = new PagamentoEntity();
		
		Optional<CartaoEntity> optional = cartaoRepository.findById(dto.getCartaoId());
		
		if(!optional.isPresent()) {
			throw new CartaoNaoExisteException("Favor informar um cartão existente");
		}
		CartaoEntity cartaoEntity = optional.get();
		
		if(!cartaoEntity.getAtivo()) {
			throw new CartaoInativoException("Favor informar um cartão ativo");
		}
		entity.setCartao(cartaoEntity);
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
