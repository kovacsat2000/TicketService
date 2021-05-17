package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MovieUpdatingTest {

    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;

    private static final Movie thegodfather = Movie.builder()
            .title("The Godfather")
            .genre("drama")
            .length(180)
            .build();

    private static final Movie updatedThegodfather = Movie.builder()
            .title("The Godfather")
            .genre("action")
            .length(170)
            .build();

    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        Console console = Mockito.mock(Console.class);
        underTest = new MovieServiceImpl(movieRepository, console);
    }

    @Test
    void testUpdatingMovie() {
        this.init();
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.update("The Godfather", "action", 170);

        Mockito.verify(movieRepository).save(updatedThegodfather);
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }
}
