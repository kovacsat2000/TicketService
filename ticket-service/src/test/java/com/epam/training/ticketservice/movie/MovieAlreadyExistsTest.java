package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MovieAlreadyExistsTest {

    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;
    private Console console;

    private static final Movie thegodfather = Movie.builder()
            .title("The Godfather")
            .genre("drama")
            .length(180)
            .build();

    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new MovieServiceImpl(movieRepository, console);
    }

    @Test
    void testMovieAlreadyExists() {
        this.init();
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.create("The Godfather", "drama", 180);

        Mockito.verify(console).printErr("This movie is already in our database.");
        Mockito.verifyNoMoreInteractions(console);
    }
}
