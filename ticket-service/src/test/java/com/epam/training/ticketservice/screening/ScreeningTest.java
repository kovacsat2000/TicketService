package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ScreeningTest {
    @Test
    void testScreeningsEquals(){
        Screening screening1 = new Screening(new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0)),
                new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10));
        Screening screening2 = new Screening(new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0)),
                new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10));


        Assertions.assertEquals(screening1, screening2);
    }

    @Test
    void testScreeningsDoNotEquals(){
        Screening screening1 = new Screening(new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0)),
                new Movie("The Godfather", "drama", 180),
                new Room("Room1", 10, 10));
        Screening screening2 = new Screening(new ScreeningID("The Godfather1", "Room2", LocalDateTime.of(2021,10,11,20,0)),
                new Movie("The Godfather1", "drama", 180),
                new Room("Room2", 10, 10));


        Assertions.assertNotEquals(screening1, screening2);
    }
}
