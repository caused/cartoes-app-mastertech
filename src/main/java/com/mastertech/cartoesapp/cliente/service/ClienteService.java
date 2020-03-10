package com.mastertech.cartoesapp.cliente.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.cliente.entity.ClienteEntity;
import com.mastertech.cartoesapp.cliente.repository.ClienteRepository;
import com.mastertech.cartoesapp.exception.ClienteNaoEncontradoException;

@Service
public class ClienteService {

	private ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public ClienteEntity criar(ClienteEntity cliente) {
		return repository.save(cliente);
	}
	
	public ClienteEntity obterClientePorId(Long id) throws ClienteNaoEncontradoException {
		Optional<ClienteEntity> clienteOptional = repository.findById(id);
		
		if(!clienteOptional.isPresent()) {
			throw new ClienteNaoEncontradoException("Cliente não existe");
		}
		
		return clienteOptional.get();
	}
}
