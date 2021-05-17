package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class RoomUpdatingTest {
    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private static final Room updatedRoom1 = new Room("Room1", 20, 20);

    private RoomRepository roomRepository;
    private RoomServiceImpl underTest;

    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        Console console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testUpdatingRoom() {
        this.init();
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.update("Room1", 20, 20);

        Mockito.verify(roomRepository).save(updatedRoom1);
        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }
}
