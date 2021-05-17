package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomDeleteWhenDoesentExists {
    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private RoomRepository roomRepository;
    private RoomServiceImpl underTest;
    private Console console;

    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testDeleteWhenRoomDoesentExists() {
        this.init();
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.delete("Room1");

        Mockito.verify(console).printErr("The room doesn't exist.");
        Mockito.verifyNoMoreInteractions(console);
    }
}
