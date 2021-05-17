package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomDeletingTest {
    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private RoomRepository roomRepository;
    private RoomServiceImpl underTest;

    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        Console console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testDeletingRoom() {
        this.init();
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.delete("Room1");

        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verify(roomRepository).deleteById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }
}
