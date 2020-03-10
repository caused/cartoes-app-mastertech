package com.mastertech.cartoesapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.converter.PagamentoConverter;
import com.mastertech.cartoesapp.dto.PagamentoDTO;
import com.mastertech.cartoesapp.entity.PagamentoEntity;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.repository.PagamentoRepository;

@Service
public class PagamentoService {

	private PagamentoRepository repository;
	private PagamentoConverter converter;
	
	public PagamentoService (PagamentoRepository repository, PagamentoConverter converter) {
		this.repository = repository;
		this.converter = converter;
	}
	
	public PagamentoDTO criarPagamento (PagamentoDTO pagamento) throws CartaoNaoExisteException, CartaoInativoException {
		PagamentoEntity pagamentoEntity = converter.convertFromDtoToEntity(pagamento);
		
		pagamentoEntity = repository.save(pagamentoEntity);
		
		return converter.convertFromEntityToDto(pagamentoEntity);
		
	}
	
	public List<PagamentoDTO> obterPagamentoPorIdCartao (Long id) throws CartaoNaoExisteException{
		List<PagamentoEntity> listaPagamentos = repository.findByCartao(id);
		
		if(listaPagamentos == null) {
			throw new CartaoNaoExisteException("Favor lançar o pagamento em um cartão existente");
		}
		
		return listaPagamentos
				.stream()
				.map(pagamento -> converter.convertFromEntityToDto(pagamento))
				.collect(Collectors.toList());
	}
}
