package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MovieDeletingTest {
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
    void testDeletingMovie() {
        this.init();
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.delete("The Godfather");

        Mockito.verify(movieRepository).deleteById("The Godfather");
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }
}
