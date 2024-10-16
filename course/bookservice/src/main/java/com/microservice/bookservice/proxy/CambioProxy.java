package com.microservice.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.bookservice.response.Cambio;

@FeignClient(name = "cambioservice", url = "localhost:8000")
public interface CambioProxy {
 
    @GetMapping(value = "/cambioservice/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount") Double amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to);

}
