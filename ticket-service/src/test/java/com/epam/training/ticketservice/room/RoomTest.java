package com.epam.training.ticketservice.room;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {
    @Test
    void testRoomsEquals(){
        Room room1 = new Room("Room1", 10, 10);
        Room room2 = new Room("Room1", 10, 10);

        Assertions.assertEquals(room1, room2);
    }

    @Test
    void testRoomsDoNotEquals(){
        Room room1 = new Room("Room1", 10, 10);
        Room room2 = new Room("Room2", 10, 10);

        Assertions.assertNotEquals(room1, room2);
    }
}
