package com.mastertech.cartoesapp.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mastertech.cartoesapp.converter.CartaoConverter;
import com.mastertech.cartoesapp.dto.AtivarCartaoDTO;
import com.mastertech.cartoesapp.dto.CartaoDTO;
import com.mastertech.cartoesapp.entity.CartaoEntity;
import com.mastertech.cartoesapp.exception.CartaoExistenteException;
import com.mastertech.cartoesapp.exception.CartaoNaoExisteException;
import com.mastertech.cartoesapp.repository.CartaoRepository;

@Service
public class CartaoService {

	private CartaoRepository cartaoRepository;
	private CartaoConverter cartaoConverter;
	
	public CartaoService (CartaoRepository repository, CartaoConverter cartaoConverter) {
		this.cartaoRepository = repository;
		this.cartaoConverter = cartaoConverter;
	}
	
	public CartaoDTO criarCartao(CartaoEntity entity) throws CartaoExistenteException{
		
		CartaoEntity cartaoEntity = cartaoRepository.findByNumero(entity.getNumero());
		
		if(Objects.isNull(cartaoEntity)) {
			CartaoEntity cartaoCriado = this.cartaoRepository.save(entity);
			
			return cartaoConverter.convertFromEntityToDto(cartaoCriado);
		}else {
			throw new CartaoExistenteException("Este número de cartão já está em uso");
		}
		
		
	}
	
	public CartaoDTO ativarCartao(String numero, AtivarCartaoDTO cartao) throws CartaoNaoExisteException {
			
			CartaoEntity cartaoEntity = cartaoRepository.findByNumero(numero);
			
			if(Objects.isNull(cartaoEntity)) {
				throw new CartaoNaoExisteException("Favor informar um cartão existente para ativar");
			}else {
				cartaoEntity.setAtivo(cartao.getAtivo());
				cartaoEntity = cartaoRepository.save(cartaoEntity);
				
				return cartaoConverter.convertFromEntityToDto(cartaoEntity);
			}
			
	}

	public CartaoDTO obterPorId(String numero) throws CartaoNaoExisteException {
			CartaoEntity cartaoEntity = cartaoRepository.findByNumero(numero);
			
			if(Objects.isNull(cartaoEntity)) {
				throw new CartaoNaoExisteException("Este cartão não existe");
			}else {
				return cartaoConverter.convertFromEntityToDto(cartaoEntity);
			}
			
		
	}
	
}
