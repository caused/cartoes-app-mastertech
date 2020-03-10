package com.mastertech.cartoesapp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.cartoesapp.converter.CartaoConverter;
import com.mastertech.cartoesapp.dto.AtivarCartaoDTO;
import com.mastertech.cartoesapp.dto.CartaoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.exception.CartaoExistenteException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.exception.UsuarioNaoExisteException;
import com.mastertech.cartoesapp.service.CartaoService;

@RestController
@RequestMapping("/cartao")
public class CartaoController {
	
	private CartaoConverter converter;
	private CartaoService service;
	
	public CartaoController (CartaoConverter converter, CartaoService service) {
		this.converter = converter;
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CartaoDTO> criarCartao (@Valid @RequestBody CartaoDTO cartao) throws UsuarioNaoExisteException, CartaoExistenteException {
		CartaoEntity entity = this.converter.convertFromDtoToEntity(cartao);
		
		CartaoDTO cartaoCriado = service.criarCartao(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCriado);
	}
	
	@PatchMapping("/{numero}")
	public ResponseEntity<CartaoDTO> ativarCartao (@PathVariable String numero, @RequestBody AtivarCartaoDTO cartao) throws CartaoNaoExisteException{
		CartaoDTO cartaoDTO = this.service.ativarCartao(numero, cartao);
		
		return ResponseEntity.ok(cartaoDTO);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<CartaoDTO> obterCartaoPorNumero (@PathVariable String numero) throws CartaoNaoExisteException {
		CartaoDTO cartaoDTO = this.service.obterPorId(numero);
		
		return ResponseEntity.ok(cartaoDTO);
	}
	
}
