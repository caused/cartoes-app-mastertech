package com.mastertech.cartoesapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.converter.PagamentoConverter;
import com.mastertech.cartoesapp.dto.PagamentoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.PagamentoEntity;
import com.mastertech.cartoesapp.exception.CartaoInativoException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.repository.PagamentoRepository;

@Service
public class PagamentoService {

	private PagamentoRepository repository;
	private PagamentoConverter converter;
	private CartaoService cartaoService;

	public PagamentoService (PagamentoRepository repository, PagamentoConverter converter,CartaoService cartaoService ) {
		this.repository = repository;
		this.converter = converter;
		this.cartaoService = cartaoService;
	}

	public PagamentoEntity criarPagamento (PagamentoDTO pagamento) throws CartaoNaoExisteException, CartaoInativoException {
		CartaoEntity cartaoEntity = cartaoService.obterPorId(pagamento.getCartaoId());

		if(!cartaoEntity.getAtivo()) {
			throw new CartaoInativoException("Favor informar um cartão ativo");
		}

		PagamentoEntity pagamentoEntity = converter.convertFromDtoToEntity(pagamento, cartaoEntity);

		return repository.save(pagamentoEntity);

	}

	public List<PagamentoEntity> obterPagamentoPorIdCartao (Long id) throws CartaoNaoExisteException{
		List<PagamentoEntity> listaPagamentos = repository.findByCartao(id);

		if(listaPagamentos == null) {
			throw new CartaoNaoExisteException("Favor lançar o pagamento em um cartão existente");
		}

		return listaPagamentos;
	}
}
