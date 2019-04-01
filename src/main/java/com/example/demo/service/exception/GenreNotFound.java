package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;

public class GenreNotFound extends BusinessException {

    public GenreNotFound() {
        super("genre.notFound", HttpStatus.NOT_FOUND);
    }
}
