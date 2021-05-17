package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.Console;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.movie.MovieRepository;
import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

public class ScreeningAddingTest {
    private ScreeningServiceImpl underTest;
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;

    private static final Movie thegodfather = Movie.builder()
            .title("The Godfather")
            .genre("drama")
            .length(180)
            .build();

    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private static final ScreeningID screeningID = new ScreeningID("The Godfather",
            "Room1",
            LocalDateTime.of(2021, 10, 11, 20, 0));

    private static final Screening screening = Screening.builder()
            .id(screeningID)
            .movie(thegodfather)
            .room(room1)
            .build();

    public void init(){
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        movieRepository = Mockito.mock(MovieRepository.class);
        Console console = Mockito.mock(Console.class);

        underTest = new ScreeningServiceImpl(screeningRepository,
                roomRepository,
                movieRepository,
                console);
    }

    @Test
    void testAddingScreeningFindingMovie() {
        this.init();

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testAddingScreeningFindingRoom() {
        this.init();

        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    void testAddingScreeningFindingScreening() {
        this.init();

        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(screeningRepository).findById(screeningID);
        Mockito.verify(screeningRepository).findAll();
        Mockito.verify(screeningRepository).save(screening);
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }
}
