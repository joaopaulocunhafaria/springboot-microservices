package com.microservice.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
