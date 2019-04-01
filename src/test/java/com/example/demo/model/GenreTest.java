package com.example.demo.model;

import com.example.demo.service.exception.BusinessException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GenreTest {

    @Test
    public void should_return_a_valid_enum_value() {
        Genre genre = Genre.getIfPresent("MYSTERY");
        assertNotNull(genre);
    }

    @Test(expected = BusinessException.class)
    public void should_thrown_an_exception_when_a_invalid_value_is_received() {
        Genre.getIfPresent("INVALID");
    }
}