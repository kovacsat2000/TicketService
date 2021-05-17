package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class RoomListEmptyTest {
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
    void testListIsEmpty() {
        this.init();
        Mockito.when(roomRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no rooms at the moment");
        Mockito.verifyNoMoreInteractions(console);
    }
}
