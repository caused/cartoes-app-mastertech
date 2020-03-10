package com.mastertech.cartoesapp.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastertech.cartoesapp.cliente.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
