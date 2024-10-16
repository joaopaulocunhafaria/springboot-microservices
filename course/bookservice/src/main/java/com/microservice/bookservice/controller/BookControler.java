package com.microservice.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bookservice.model.Book;
import com.microservice.bookservice.proxy.CambioProxy;
import com.microservice.bookservice.repository.BookRepository;
import com.microservice.bookservice.response.Cambio;

@RestController
@RequestMapping("bookservice")
public class BookControler {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {

        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        String port = environment.getProperty("local.server.port");

        book.setEnviroment("BOOK port: " + port + " Cambio port: " + cambio.getEnvironment() + " FEIGN");
        book.setCurrency(currency);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

}
