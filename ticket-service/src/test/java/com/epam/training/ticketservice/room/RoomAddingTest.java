package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RoomAddingTest {
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
    void testAddingMovie() {
        this.init();
        underTest.create("Room1", 10, 10);

        Mockito.verify(roomRepository).save(room1);
        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }
}
