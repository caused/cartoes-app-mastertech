package com.mastertech.cartoesapp.pagamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.mastertech.cartoesapp.pagamento.entity.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long>{

	@Nullable
	@Query("select p from PagamentoEntity p inner join p.cartao c where c.id = :id")
	public List<PagamentoEntity> findByCartao(Long id);
}
