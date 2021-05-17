package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.Console;
import com.epam.training.ticketservice.movie.MovieRepository;
import com.epam.training.ticketservice.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ScreeningListEmptyTest {
    private ScreeningServiceImpl underTest;
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;
    private Console console;

    public void init(){
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        movieRepository = Mockito.mock(MovieRepository.class);
        console = Mockito.mock(Console.class);

        underTest = new ScreeningServiceImpl(screeningRepository,
                roomRepository,
                movieRepository,
                console);
    }

    @Test
    void testListIsEmpty() {
        this.init();
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no screenings");
        Mockito.verifyNoMoreInteractions(console);
    }
}
