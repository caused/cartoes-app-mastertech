package com.mastertech.cartoesapp.cartao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mastertech.cartoesapp.cliente.entity.ClienteEntity;
import com.mastertech.cartoesapp.pagamento.entity.PagamentoEntity;

@Table (name = "cartao")
@Entity
public class CartaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "clienteId")
	private ClienteEntity cliente;
	
	@OneToMany(mappedBy = "cartao")
	private List<PagamentoEntity> pagamentos;
	
	private String numero;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
}

