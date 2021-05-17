package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MovieAddingTest {

    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;

    private static final Movie thegodfather = Movie.builder()
            .title("The Godfather")
            .genre("drama")
            .length(180)
            .build();

    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        Console console = Mockito.mock(Console.class);
        underTest = new MovieServiceImpl(movieRepository, console);
    }

    @Test
    void testAddingMovie() {
        this.init();

        underTest.create("The Godfather", "drama", 180);

        Mockito.verify(movieRepository).save(thegodfather);
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }
}
