package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomUpdateWhenDoesentExists {
    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private static final Room updatedRoom1 = new Room("Room1", 20, 20);

    private RoomRepository roomRepository;
    private RoomServiceImpl underTest;
    private Console console;

    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testUpdatingRoomWhenDoesentExists() {
        this.init();
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.update("Room1", 10, 10);

        Mockito.verify(console).printErr("There is no room with name: %s", "Room1");
        Mockito.verifyNoMoreInteractions(console);
    }
}
