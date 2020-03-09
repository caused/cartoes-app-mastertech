package com.mastertech.cartoesapp.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.cartoesapp.dto.ClienteDTO;
import com.mastertech.cartoesapp.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteService service;

	public ClienteController (ClienteService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO cliente) {
		ClienteDTO clienteDTO = service.criar(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obterClientePorId (@PathVariable Long id) {
		ClienteDTO clienteDTO = service.obterClientePorId(id);
		
		return Objects.isNull(clienteDTO)
				? ResponseEntity.notFound().build()
				: ResponseEntity.ok(clienteDTO);
	}
}
