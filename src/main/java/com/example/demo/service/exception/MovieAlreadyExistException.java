package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;

public class MovieAlreadyExistException extends BusinessException {

    public MovieAlreadyExistException() {
        super("movie.alreadyExists", HttpStatus.CONFLICT);
    }
}
