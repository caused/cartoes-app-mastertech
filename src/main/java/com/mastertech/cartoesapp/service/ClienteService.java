package com.mastertech.cartoesapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.converter.ClienteConverter;
import com.mastertech.cartoesapp.dto.ClienteDTO;
import com.mastertech.cartoesapp.repository.ClienteRepository;
import com.mastertech.cartoesapp.entity.ClienteEntity;

@Service
public class ClienteService {

	private ClienteRepository repository;
	private ClienteConverter converter;
	
	public ClienteService(ClienteRepository repository, ClienteConverter converter) {
		this.repository = repository;
		this.converter = converter;
	}
	
	public ClienteDTO criar(ClienteDTO cliente) {
		ClienteEntity entity = converter.convertFromDtoToEntity(cliente);
		
		entity = repository.save(entity);
		
		return converter.convertFromEntityToDto(entity);
	}
	
	public ClienteDTO obterClientePorId(Long id) {
		Optional<ClienteEntity> clienteOptional = repository.findById(id);
		
		return clienteOptional.isPresent() 
				? converter.convertFromEntityToDto(clienteOptional.get())
				:	null;
	}
}
