package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class RoomServiceImplTest {
    private static final Room room1 = Room.builder()
            .name("Room1")
            .rows(10)
            .columns(10)
            .build();

    private static final Room updatedRoom1 = new Room("Room1", 20, 20);

    private RoomRepository roomRepository;
    private Console console;
    private RoomServiceImpl underTest;

    @BeforeEach
    public void init(){
        roomRepository = Mockito.mock(RoomRepository.class);
        console = Mockito.mock(Console.class);
        underTest = new RoomServiceImpl(roomRepository, console);
    }

    @Test
    void testListIsEmpty() {
        Mockito.when(roomRepository.findAll()).thenReturn(List.of());

        underTest.listAll();

        Mockito.verify(console).printErr("There are no rooms at the moment");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testAddingMovie() {
        underTest.create("Room1", 10, 10);

        Mockito.verify(roomRepository).save(room1);
        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    void testRoomAlreadyExists() {
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.create("Room1", 10, 10);

        Mockito.verify(console).printErr("This room is already exists");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testUpdatingRoom() {
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.update("Room1", 20, 20);

        Mockito.verify(roomRepository).save(updatedRoom1);
        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    void testUpdatingRoomWhenDoesentExists() {
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.update("Room1", 10, 10);

        Mockito.verify(console).printErr("There is no room with name: %s", "Room1");
        Mockito.verifyNoMoreInteractions(console);
    }

    @Test
    void testDeletingRoom() {
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.of(room1));

        underTest.delete("Room1");

        Mockito.verify(roomRepository).findById("Room1");
        Mockito.verify(roomRepository).deleteById("Room1");
        Mockito.verifyNoMoreInteractions(roomRepository);
    }

    @Test
    void testDeleteWhenRoomDoesentExists() {
        Mockito.when(roomRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        underTest.delete("Room1");

        Mockito.verify(console).printErr("The room doesn't exist.");
        Mockito.verifyNoMoreInteractions(console);
    }
}
