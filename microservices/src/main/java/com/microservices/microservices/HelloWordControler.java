package com.microservices.microservices;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordControler {

    private static final String template = "Hello, %s";
    private AtomicLong counter = new AtomicLong();

    @RequestMapping(path = "/hello")
    public HelloWord hello(@RequestParam(value = "name", defaultValue = "world") String name) {
     
        return new HelloWord(counter.incrementAndGet(), String.format(template, name));
    }
}
