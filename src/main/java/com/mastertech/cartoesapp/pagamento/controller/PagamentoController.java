package com.mastertech.cartoesapp.pagamento.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.pagamento.converter.PagamentoConverter;
import com.mastertech.cartoesapp.pagamento.dto.PagamentoDTO;
import com.mastertech.cartoesapp.pagamento.entity.PagamentoEntity;
import com.mastertech.cartoesapp.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
	
	private PagamentoService service;
	private PagamentoConverter converter;
	
	public PagamentoController (PagamentoService service, PagamentoConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@PostMapping
	public ResponseEntity<PagamentoDTO> criarPagamento (@Valid @RequestBody PagamentoDTO pagamento) throws CartaoNaoExisteException, CartaoInativoException {
		
		PagamentoEntity pagamentoEntity = service.criarPagamento(pagamento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertFromEntityToDto(pagamentoEntity));
		
	}
	
	@GetMapping("/{id_cartao}")
	public List<PagamentoDTO> obterPagamentosDeUmCartao(@PathVariable(name = "id_cartao") Long id) throws CartaoNaoExisteException {
		List<PagamentoEntity> pagamentosList = this.service.obterPagamentoPorIdCartao(id);
		
		return pagamentosList
				.stream()
				.map(pagamento -> converter.convertFromEntityToDto(pagamento))
				.collect(Collectors.toList());
	}
}
