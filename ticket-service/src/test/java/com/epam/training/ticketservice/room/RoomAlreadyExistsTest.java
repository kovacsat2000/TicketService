package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomAlreadyExistsTest {

    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private RoomRepository roomRepository;
    private Console console;
    private RoomServiceImpl underTest;

    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testRoomAlreadyExists() {
        this.init();
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.create("Room1", 10, 10);

        Mockito.verify(console).printErr("This room is already exists");
        Mockito.verifyNoMoreInteractions(console);
    }
}
