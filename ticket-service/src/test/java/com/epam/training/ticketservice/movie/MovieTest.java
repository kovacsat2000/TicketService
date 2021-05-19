package com.epam.training.ticketservice.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieTest {
    @Test
    void testMoviesEquals(){
        Movie movie1 = new Movie("The Godfather", "drama", 180);
        Movie movie2 = new Movie("The Godfather", "drama", 180);

        Assertions.assertEquals(movie1, movie2);
    }

    @Test
    void testMoviesDoNotEquals(){
        Movie movie1 = new Movie("The Godfather", "drama", 180);
        Movie movie2 = new Movie("The Godfather1", "drama", 180);

        Assertions.assertNotEquals(movie1, movie2);
    }
}
