package com.epam.training.ticketservice.screening;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ScreeningIDTest {
    @Test
    void testScreeningIDsEquals(){
        ScreeningID screeningId1 = new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0));
        ScreeningID screeningId2 = new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0));

        Assertions.assertEquals(screeningId1, screeningId2);
    }

    @Test
    void testScreeningIDsDoNotEquals(){
        ScreeningID screeningId1 = new ScreeningID("The Godfather", "Room1", LocalDateTime.of(2021,10,11,20,0));
        ScreeningID screeningId2 = new ScreeningID("The Godfather1", "Room2", LocalDateTime.of(2021,10,11,23,0));

        Assertions.assertNotEquals(screeningId1, screeningId2);
    }
}
