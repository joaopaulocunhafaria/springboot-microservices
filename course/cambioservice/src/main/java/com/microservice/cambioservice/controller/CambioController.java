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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio Endpoint")
@RestController
@RequestMapping("cambioservice")
public class CambioController {

    @Autowired
    private Environment environment;

    @Autowired
    private CambioRepository repository;

    @Operation(summary = "Get the converted vaues of differents country`s coin by passing the amount, the source and to what coin you want to convert")
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
