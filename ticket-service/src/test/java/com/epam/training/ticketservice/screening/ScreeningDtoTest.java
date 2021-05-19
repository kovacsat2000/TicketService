package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ScreeningDtoTest {
    @Test
    void testScreeningDtosEquals(){
        ScreeningDto screeningDto1 = new ScreeningDto(new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10),
                LocalDateTime.of(2021,10,11,20,0));
        ScreeningDto screeningDto2 = new ScreeningDto(new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10),
                LocalDateTime.of(2021,10,11,20,0));

        Assertions.assertEquals(screeningDto1, screeningDto2);
    }

    @Test
    void testScreeningDtosDoNotEquals(){
        ScreeningDto screeningDto1 = new ScreeningDto(new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10),
                LocalDateTime.of(2021,10,11,20,0));
        ScreeningDto screeningDto2 = new ScreeningDto(new Movie("The Godfather1", "drama", 180),
                new Room("Room2", 10, 10),
                LocalDateTime.of(2021,10,11,23,0));

        Assertions.assertNotEquals(screeningDto1, screeningDto2);
    }
}
