package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class MovieServiceImplTest {
    private MovieServiceImpl underTest;
    private MovieRepository movieRepository;
    private Console console;

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

    @BeforeEach
    public void init(){
        movieRepository = Mockito.mock(MovieRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new MovieServiceImpl(movieRepository, console);
    }

    @Test
    void testListIsEmpty() {
        Mockito.when(movieRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no movies at the moment");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testAddingMovie() {

        underTest.create("The Godfather", "drama", 180);

        Mockito.verify(movieRepository).save(thegodfather);
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testMovieAlreadyExists() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.create("The Godfather", "drama", 180);

        Mockito.verify(console).printErr("This movie is already in our database.");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testUpdatingMovie() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.update("The Godfather", "action", 170);

        Mockito.verify(movieRepository).save(updatedThegodfather);
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testUpdateWhenMovieDoesentExists() {

        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.update("The Godfather", "action", 170);

        Mockito.verify(console).printErr("There is no movie with title: %s", "The Godfather");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testDeletingMovie() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.delete("The Godfather");

        Mockito.verify(movieRepository).deleteById("The Godfather");
        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testDeleteWhenMovieDoesentExists() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.delete("The Godfather");

        Mockito.verify(console).printErr("The movie doesn't exist.");
        Mockito.verifyNoMoreInteractions(console);
    }
}
