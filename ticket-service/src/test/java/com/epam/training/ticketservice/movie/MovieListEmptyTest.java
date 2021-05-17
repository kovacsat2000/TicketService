package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class MovieListEmptyTest {

    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;
    private Console console;

    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new MovieServiceImpl(movieRepository, console);
    }

    @Test
    void testListIsEmpty() {
        this.init();
        Mockito.when(movieRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no movies at the moment");
        Mockito.verifyNoMoreInteractions(console);
    }
}
