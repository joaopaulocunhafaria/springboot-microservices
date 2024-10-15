package com.microservice.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.cambioservice.models.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Cambio findByFromAndTo(String from,String to);
    
}
