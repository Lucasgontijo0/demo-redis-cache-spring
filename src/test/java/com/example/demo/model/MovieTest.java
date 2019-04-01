package com.example.demo.model;

import com.example.demo.stub.MovieStub;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovieTest {

    @Test
    public void should_validate_movie_action_to_create() {

        Movie movie = MovieStub.dummyMovie();

        assertTrue(movie.isNew());
        assertFalse(movie.isUpdate());
    }

    @Test
    public void should_validate_movie_action_to_update() {

        Movie movie = MovieStub.dummyMovie();
        movie.setId(1L);

        assertTrue(movie.isUpdate());
        assertFalse(movie.isNew());
    }
}