package com.mastertech.cartoesapp.cliente.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.cartoesapp.cliente.converter.ClienteConverter;
import com.mastertech.cartoesapp.cliente.dto.ClienteDTO;
import com.mastertech.cartoesapp.cliente.entity.ClienteEntity;
import com.mastertech.cartoesapp.cliente.service.ClienteService;
import com.mastertech.cartoesapp.exception.ClienteNaoEncontradoException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteConverter converter;
	private ClienteService service;

	public ClienteController (ClienteService service, ClienteConverter converter) {
		this.service = service;
		this.converter = converter;
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO cliente) {
		ClienteEntity entity = converter.convertFromDtoToEntity(cliente);
		
		entity = service.criar(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertFromEntityToDto(entity));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obterClientePorId (@PathVariable Long id) throws ClienteNaoEncontradoException {
		ClienteEntity entity = service.obterClientePorId(id);
		
		return ResponseEntity.ok(converter.convertFromEntityToDto(entity));
	}
}
