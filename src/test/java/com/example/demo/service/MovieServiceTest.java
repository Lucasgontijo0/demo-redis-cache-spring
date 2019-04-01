package com.example.demo.service;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.exception.MovieAlreadyExistException;
import com.example.demo.service.exception.MovieNotFoundException;
import com.example.demo.stub.MovieStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void setUp() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void should_create_movie() {

        Movie savedMovie = MovieStub.dummyMovie();
        savedMovie.setId(1L);

        Movie createMovie = MovieStub.dummyMovie();
        when(this.movieRepository.save(createMovie)).thenReturn(savedMovie);

        Movie resultMovie = this.movieService.saveMovie(createMovie);
        assertEquals(savedMovie, resultMovie);
    }

    @Test(expected = MovieAlreadyExistException.class)
    public void should_thrown_an_exception_when_a_existing_movie_is_requested_to_be_created() {

        Movie existingMovie = MovieStub.dummyMovie();
        existingMovie.setId(1L);

        when(this.movieRepository.findByTitleAndGenre("THE GODFATHER", Genre.DRAMA))
                .thenReturn(Optional.of(existingMovie));

        Movie createMovie = MovieStub.dummyMovie();
        this.movieService.saveMovie(createMovie);
    }

    @Test
    public void should_update_an_movie() {

        Movie existingMovie = MovieStub.dummyMovie();
        existingMovie.setId(1L);

        when(this.movieRepository.findByTitleAndGenre("THE GODFATHER", Genre.DRAMA))
                .thenReturn(Optional.of(existingMovie));

        Movie updateMovie = MovieStub.dummyMovie();
        updateMovie.setId(1L);

        this.movieService.saveMovie(updateMovie);
        verify(this.movieRepository, times(1)).save(updateMovie);
    }

    @Test
    public void should_delete_an_movie() {

        Long movieId = 1L;
        Movie existingMovie = MovieStub.dummyMovie();
        existingMovie.setId(movieId);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(existingMovie));

        this.movieService.deleteById(movieId);
        verify(movieRepository, times(1)).delete(existingMovie);
    }

    @Test(expected = MovieNotFoundException.class)
    public void should_thrown_exception_when_invalid_id_is_informed() {

        Long movieId = 999L;
        when(movieRepository.findById(movieId)).thenThrow(MovieNotFoundException.class);

        this.movieService.deleteById(movieId);
    }

    @Test
    public void should_list_all_movies() {

        List<Movie> movies = MovieStub.theGodFatherTrilogy();
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> allMovies = this.movieService.listAllMovies();

        assertNotNull(allMovies);
        allMovies.forEach(
                movie -> {
                    assertNotNull(movie.getId());
                    assertNotNull(movie.getTitle());
                    assertNotNull(movie.getGenre());
                    assertNotNull(movie.getLanguage());
                    assertNotNull(movie.getYear());
                }
        );
    }

    @Test
    public void should_list_movies_by_genres() {

        List<Movie> movies = MovieStub.theGodFatherTrilogy();
        movies.add(new Movie(4L, "Pulp Fiction", 1994, "English", Genre.DRAMA));

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> dramaMovies = this.movieService.listMovieByGenre("DRAMA");
        assertNotNull(dramaMovies);
        dramaMovies.forEach(
                movie -> {
                    assertNotNull(movie.getId());
                    assertNotNull(movie.getTitle());
                    assertNotNull(movie.getGenre());
                    assertNotNull(movie.getLanguage());
                    assertNotNull(movie.getYear());
                }
        );
    }

    @Test
    public void should_return_an_empty_list_when_movies_by_genre_is_not_found() {

        when(this.movieRepository.findAllByGenre(Genre.MYSTERY)).thenReturn(Collections.emptyList());
        List<Movie> movies = this.movieService.listMovieByGenre("MYSTERY");

        assertTrue(movies.isEmpty());
    }

    @Test
    public void should_find_movie_by_id() {

        Long movieId = 1L;

        Movie movie = MovieStub.dummyMovie();
        movie.setId(1L);

        when(this.movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie movieById = this.movieService.findMovieById(movieId);
        assertNotNull(movieById);
    }

    @Test(expected = MovieNotFoundException.class)
    public void should_thrown_exception_when_movie_by_id_is_not_found() {

        Long movieId = 999L;

        when(this.movieRepository.findById(movieId)).thenThrow(MovieNotFoundException.class);

        this.movieService.findMovieById(movieId);
    }
}