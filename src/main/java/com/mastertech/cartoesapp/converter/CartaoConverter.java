package com.mastertech.cartoesapp.converter;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.dto.CartaoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.ClienteEntity;

@Component
public class CartaoConverter {
	
	
	public CartaoEntity convertFromDtoToEntity (CartaoDTO dto){
		CartaoEntity entity = new CartaoEntity();
		
		ClienteEntity cliente = new ClienteEntity();
		cliente.setId(dto.getClienteId());
		
		entity.setCliente(cliente);
		entity.setNumero(dto.getNumero());
		
		return entity;   
	}
	
	public CartaoEntity convertFromDtoToEntity (CartaoDTO dto, ClienteEntity cliente){
		CartaoEntity entity = new CartaoEntity();
		
		entity.setAtivo(Boolean.FALSE);
		entity.setCliente(cliente);
		entity.setNumero(dto.getNumero());
		
		return entity;   
	}
	
	public CartaoDTO convertFromEntityToDto (CartaoEntity entity) {
		CartaoDTO dto = new CartaoDTO();
		
		dto.setAtivo(entity.getAtivo());
		dto.setClienteId(entity.getCliente().getId());
		dto.setNumero(entity.getNumero());
		dto.setId(entity.getId());
		
		return dto;
	}
}
