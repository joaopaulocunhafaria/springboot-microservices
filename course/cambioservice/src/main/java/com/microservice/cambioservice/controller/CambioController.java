package com.microservice.cambioservice.controller;

import java.math.BigDecimal;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.cambioservice.models.Cambio;
import com.microservice.cambioservice.repository.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {

        Cambio cambio = repository.findByFromAndTo(from, to);

        if (cambio == null) throw new RuntimeException("Currency unsuported");

        String port = environment.getProperty("local.server.port");
  
        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount); 

        cambio.setEnvironment(port);
        cambio.setConvertedValue(convertedValue );
    
        
        return cambio;
    }

}
