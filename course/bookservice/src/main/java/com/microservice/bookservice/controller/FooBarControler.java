package com.microservice.bookservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 

@RestController
@RequestMapping("bookservice")
public class FooBarControler {
 
    private Logger logger = LoggerFactory.getLogger(FooBarControler.class);

    @GetMapping("/foobar")
    @Retry(name = "foobar")
    public String fooBar() {
        logger.info("Request to foobar is received!!");
        var response = new RestTemplate().getForEntity("http://localhost:8000/foobar", String.class);

        return response.getBody();
    }

}