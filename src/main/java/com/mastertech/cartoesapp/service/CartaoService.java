package com.mastertech.cartoesapp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.dto.AtivarCartaoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.entity.ClienteEntity;
import com.mastertech.cartoesapp.exception.CartaoExistenteException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.exception.ClienteNaoEncontradoException;
import com.mastertech.cartoesapp.repository.CartaoRepository;

@Service
public class CartaoService {

	private CartaoRepository cartaoRepository;
	private ClienteService clienteService;

	public CartaoService (CartaoRepository repository,ClienteService clienteService) {
		this.cartaoRepository = repository;
		this.clienteService = clienteService;
	}

	public CartaoEntity criarCartao(CartaoEntity cartao) throws CartaoExistenteException, ClienteNaoEncontradoException{

		ClienteEntity clienteEntity = clienteService.obterClientePorId(cartao.getCliente().getId());
		
		cartao.setCliente(clienteEntity);
		
		CartaoEntity cartaoEntity = cartaoRepository.findByNumero(cartao.getNumero());
		
		if(!Objects.isNull(cartaoEntity)) {
			throw new CartaoExistenteException("Este número de cartão já está em uso");
		}

		return this.cartaoRepository.save(cartao);

	}

	public CartaoEntity ativarCartao(String numero, AtivarCartaoDTO cartao) throws CartaoNaoExisteException {

		CartaoEntity cartaoEntity = cartaoRepository.findByNumero(numero);

		if(Objects.isNull(cartaoEntity)) {
			throw new CartaoNaoExisteException("Favor informar um cartão existente para ativar");
		}else {
			cartaoEntity.setAtivo(cartao.getAtivo());
			return cartaoRepository.save(cartaoEntity);
		}

	}

	public CartaoEntity obterPorNumero(String numero) throws CartaoNaoExisteException {
		CartaoEntity cartaoEntity = cartaoRepository.findByNumero(numero);

		if(Objects.isNull(cartaoEntity)) {
			throw new CartaoNaoExisteException("Este cartão não existe");
		}else {
			return cartaoEntity;
		}


	}

	public CartaoEntity obterPorId(Long cartaoId) throws CartaoNaoExisteException {
		Optional<CartaoEntity> optional = cartaoRepository.findById(cartaoId);

		if(!optional.isPresent()) {
			throw new CartaoNaoExisteException("Este cartão não existe");
		}else {
			return optional.get();
		}
	}

}
