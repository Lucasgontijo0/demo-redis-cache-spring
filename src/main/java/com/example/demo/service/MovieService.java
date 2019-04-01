package com.example.demo.service;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.exception.MovieAlreadyExistException;
import com.example.demo.service.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        verifyIfMovieExists(movie);
        return this.movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        Movie movieToDelete = this.movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
        this.movieRepository.delete(movieToDelete);
    }

    public Movie findMovieById(Long id) {
        return this.movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    public List<Movie> listMovieByGenre(String genreName) {
        Genre genre = Genre.getIfPresent(genreName);
        return this.movieRepository.findAllByGenre(genre);
    }

    private void verifyIfMovieExists(Movie movie) {

        Optional<Movie> byTitleAndGenre = this.movieRepository.findByTitleAndGenre(movie.getTitle(), movie.getGenre());

        if (byTitleAndGenre.isPresent() && (movie.isNew() || isUpdatingToADifferentMovie(movie, byTitleAndGenre.get()))) {
            throw new MovieAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentMovie(Movie movie, Movie byTitleAndGenre) {
        return movie.isUpdate() && !byTitleAndGenre.getId().equals(movie.getId());
    }


}
