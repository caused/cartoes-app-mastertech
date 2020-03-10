package com.mastertech.cartoesapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.cartoesapp.dto.PagamentoDTO;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
	
	private PagamentoService service;
	
	public PagamentoController (PagamentoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<PagamentoDTO> criarPagamento (@Valid @RequestBody PagamentoDTO pagamento) throws CartaoNaoExisteException, CartaoInativoException {
		
		PagamentoDTO pagamentoDTO = service.criarPagamento(pagamento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoDTO);
		
	}
	
	@GetMapping("/{id_cartao}")
	public List<PagamentoDTO> obterPagamentosDeUmCartao(@PathVariable(name = "id_cartao") Long id) throws CartaoNaoExisteException {
		return this.service.obterPagamentoPorIdCartao(id);
	}
}
