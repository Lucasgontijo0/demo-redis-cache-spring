package com.example.demo.repository;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitleAndGenre(String title, Genre genre);

    List<Movie> findAllByGenre(Genre genre);
}
