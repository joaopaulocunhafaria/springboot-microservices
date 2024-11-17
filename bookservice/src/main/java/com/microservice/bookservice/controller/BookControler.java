package com.microservice.bookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.bookservice.model.Book;
import com.microservice.bookservice.proxy.CambioProxy;
import com.microservice.bookservice.repository.BookRepository;
import com.microservice.bookservice.response.Cambio;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("bookservice")
public class BookControler {

    private Logger logger = LoggerFactory.getLogger(BookControler.class);

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    
    @Operation(summary = "Find your book passing the ID as search key")
    @GetMapping(value = "/{id}/{currency}")
    @Retry(name = "findbook", fallbackMethod = "returnDefaultBook")
    @CircuitBreaker(name = "findbook", fallbackMethod = "returnDefaultBook")
    @RateLimiter(name = "findbook", fallbackMethod = "returnDefaultBook")
    @Bulkhead(name = "findbook", fallbackMethod = "returnDefaultBook")
    @TimeLimiter(name = "findbook", fallbackMethod = "returnDefaultBook")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {
        
        //Forcing error using wrong URL to force the use of fallbackMethod            
        var response = new RestTemplate().getForEntity("http://localhost:8000/foobar", String.class);

        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        String port = environment.getProperty("local.server.port");

        book.setEnviroment("BOOK port: " + port + " Cambio port: " + cambio.getEnvironment() + " FEIGN");
        book.setCurrency(currency);
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

    public Book returnDefaultBook(Exception e) {

        logger.info("Returning default book ");

        Long defaultID = 1L;

        Book book = repository.findById(defaultID).orElseThrow(() -> new RuntimeException("Book not found"));
        return book;
    }

}
