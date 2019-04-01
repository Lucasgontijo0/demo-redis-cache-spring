package com.example.demo.model;

import com.example.demo.service.exception.GenreNotFound;
import com.google.common.base.Enums;

public enum Genre {

    COMEDY,
    SCI_FI,
    HORROR,
    ROMANCE,
    ACTION,
    THRILLER,
    DRAMA,
    MYSTERY,
    CRIME,
    ANIMATION,
    ADVENTURE,
    FANTASY,
    COMEDY_ROMANCE,
    ACTION_COMEDY,
    SUPERHERO;

    public static Genre getIfPresent(String value) {
        Genre genre = Enums.getIfPresent(Genre.class, value).orNull();
        if (genre == null) {
            throw new GenreNotFound();
        }
        return genre;
    }

}
