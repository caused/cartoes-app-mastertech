package com.mastertech.cartoesapp.converter;

import org.springframework.stereotype.Component;

import com.mastertech.cartoesapp.dto.ClienteDTO;
import com.mastertech.cartoesapp.entity.ClienteEntity;

@Component
public class ClienteConverter {

	public ClienteEntity convertFromDtoToEntity (ClienteDTO dto) {
		ClienteEntity entity = new ClienteEntity();
		
		entity.setNome(dto.getNome());
		
		return entity;
	}
	
	public ClienteDTO convertFromEntityToDto (ClienteEntity entity) {
		ClienteDTO dto = new ClienteDTO();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		
		return dto;
	}
}
