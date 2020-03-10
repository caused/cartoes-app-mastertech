package com.mastertech.cartoesapp.exception;

public class UsuarioNaoExisteException extends Exception {

	/**
	 * 
	 */
	
	private String mensagem;
	
	private static final long serialVersionUID = -775495078323921570L;

	public UsuarioNaoExisteException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}
	
	public String getMensagem () {
		return this.mensagem;
	}
 	
}
