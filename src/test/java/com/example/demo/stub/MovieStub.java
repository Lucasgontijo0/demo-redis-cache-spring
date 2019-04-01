package com.example.demo.stub;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieStub {

    public static Movie dummyMovie() {
        Movie movie = new Movie();
        movie.setId(null);
        movie.setTitle("THE GODFATHER");
        movie.setGenre(Genre.DRAMA);
        movie.setLanguage("English");
        movie.setYear(1972);
        return movie;
    }

    public static List<Movie> theGodFatherTrilogy() {

        List<Movie> trilogy = new ArrayList<>();

        Movie firstMovie = MovieStub.dummyMovie();
        firstMovie.setId(1L);

        Movie secondMovie = MovieStub.dummyMovie();
        secondMovie.setId(2L);
        secondMovie.setTitle("THE GODFATHER II");
        secondMovie.setYear(1974);

        Movie thirdMovie = MovieStub.dummyMovie();
        thirdMovie.setId(3L);
        thirdMovie.setTitle("THE GODFATHER III");
        thirdMovie.setYear(1990);

        trilogy.add(firstMovie);
        trilogy.add(secondMovie);
        trilogy.add(thirdMovie);

        return trilogy;
    }
}
