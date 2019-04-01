package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends BusinessException {

    public MovieNotFoundException() {
        super("movie.notFound", HttpStatus.NOT_FOUND);
    }
}
