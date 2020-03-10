package com.mastertech.cartoesapp.converter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.dto.CartaoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.ClienteEntity;
import com.mastertech.cartoesapp.exception.UsuarioNaoExisteException;
import com.mastertech.cartoesapp.repository.ClienteRepository;

@Component
public class CartaoConverter {
	
	private ClienteRepository clienteRepository;
	
	public CartaoConverter (ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	
	public CartaoEntity convertFromDtoToEntity (CartaoDTO dto) throws UsuarioNaoExisteException {
		CartaoEntity entity = new CartaoEntity();
		
		Optional<ClienteEntity> clienteOptional = clienteRepository.findById(dto.getClienteId());
		
		if(!clienteOptional.isPresent()) {
			throw new UsuarioNaoExisteException("Usuário não encontrado");
		}
		
		entity.setAtivo(Boolean.FALSE);
		entity.setCliente(clienteOptional.get());
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
