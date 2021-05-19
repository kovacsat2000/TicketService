package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.Console;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.movie.MovieRepository;
import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.room.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ScreeningServiceImplTest {
    private ScreeningServiceImpl underTest;
    private ScreeningRepository screeningRepository;
    private RoomRepository roomRepository;
    private MovieRepository movieRepository;
    private Console console;

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
    private static final ScreeningID overlapScreeningID = new ScreeningID("The Godfather",
            "Room1",
            LocalDateTime.of(2021, 10, 11, 19, 0));

    private static final Screening screening = Screening.builder()
            .id(screeningID)
            .movie(thegodfather)
            .room(room1)
            .build();

    private static final ScreeningDto screeningDto = ScreeningDto.builder()
            .movie(thegodfather)
            .room(room1)
            .start(LocalDateTime.of(2021, 10, 11, 20, 0))
            .build();

    private static final Screening overlapScreening = new Screening(overlapScreeningID, thegodfather, room1);

    @BeforeEach
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
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no screenings");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testAddingScreeningFindingMovie() {
        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(movieRepository).findById("The Godfather");
        Mockito.verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void testAddingScreeningFindingRoom() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    void testAddingScreeningFindingScreening() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(screeningRepository).findById(screeningID);
        Mockito.verify(screeningRepository).findAll();
        Mockito.verify(screeningRepository).save(screening);
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    void testOutputErrorWhenTheTimeFormatNotValid() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.create("The Godfather", "Room1", "2021.10.11 20:00");

        Mockito.verify(console).printErr("The start time has to match the required format! ( YYYY-MM-DD hh:mm )");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testOutputErrorWhenTheMovieDoesentExists() {
        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(console).printErr("The movie The Godfather doesn't exists");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testOutputErrorWhenTheRoomDoesentExists() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(console).printErr("The room Room1 doesn't exists");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testOutputErrorWhenTheScreeningAlreadyExists() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));
        Mockito.when(screeningRepository.findById(screeningID)).thenReturn(Optional.of(screening));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(console).printErr("This screening is already exists");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testOutputErrorWhenItIsOverlap() {
        Mockito.when(movieRepository.findById(Mockito.anyString())).thenReturn(Optional.of(thegodfather));
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));
        Mockito.when(screeningRepository.findAll()).thenReturn(List.of(overlapScreening));

        underTest.create("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(console).printErr("There is an overlapping screening");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testDeletingScreening() {
        Mockito.when(screeningRepository.findById(Mockito.any(ScreeningID.class))).thenReturn(Optional.of(screening));

        underTest.delete("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(screeningRepository).deleteById(screeningID);
        Mockito.verify(screeningRepository).findById(screeningID);
        Mockito.verifyNoMoreInteractions(screeningRepository);
    }

    @Test
    void testDeleteWhenScreeningDoesentExists() {
        Mockito.when(screeningRepository.findById(Mockito.any(ScreeningID.class))).thenReturn(Optional.empty());

        underTest.delete("The Godfather", "Room1", "2021-10-11 20:00");

        Mockito.verify(console).printErr("This screening doesn't exists");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testConvertToScreeningDto() {
        ScreeningDto actual = underTest.convertToScreeningDto(screening);

        Assertions.assertEquals(screeningDto, actual);
    }
}
