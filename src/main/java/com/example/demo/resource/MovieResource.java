package com.example.demo.resource;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieResource {

    private final MovieService movieService;

    @Autowired
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie) {
        return this.movieService.saveMovie(movie);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie updateMovie(@PathVariable("id") Long id, @Valid @RequestBody Movie movie) {
        movie.setId(id);
        return this.movieService.saveMovie(movie);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable("id") Long id) {
        this.movieService.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> listMovies() {
        return this.movieService.listAllMovies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie listMovieById(@PathVariable("id") Long id) {
        return this.movieService.findMovieById(id);
    }

    @GetMapping("/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findMoviesByGenre(@PathVariable("genre") String genre) {
        return this.movieService.listMovieByGenre(genre);
    }
}
