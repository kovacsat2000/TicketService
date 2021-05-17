package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.movie.Movie;

import java.util.Collection;

public interface MovieService {

    void listAll();

    void create(String title, String genre, int length);

    void update(String title, String genre, int length);

    void delete(String title);

    Collection<Movie> findByTitle(String title);

}
