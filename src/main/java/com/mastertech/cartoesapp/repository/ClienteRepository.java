package com.mastertech.cartoesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastertech.cartoesapp.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
