package com.epam.training.ticketservice.room;

import com.epam.training.ticketservice.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.training.ticketservice.Console;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl  implements RoomService {
    private final RoomRepository roomRepository;
    private final Console console;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, Console console) {
        this.roomRepository = roomRepository;
        this.console = console;
    }

    @Override
    public void listAll() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            console.printErr("There are no rooms at the moment");
        } else {
            rooms.stream()
                    .map(Room::toString)
                    .forEach(console::print);
        }
    }

    @Override
    public void create(String name, int rows, int columns) {
        if (roomRepository.findById(name).isPresent()) {
            console.printErr("This room is already exists");
        } else {
            roomRepository.save(
                    Room.builder()
                            .name(name)
                            .rows(rows)
                            .columns(columns)
                            .build());

        }
    }

    @Override
    public void update(String name, int rows, int columns) {
        if (roomRepository.findById(name).isEmpty()) {
            console.printErr("There is no room with name: %s", name);
        } else {
            roomRepository.save(
                    Room.builder()
                            .name(name)
                            .rows(rows)
                            .columns(columns)
                            .build());

        }
    }

    @Override
    public void delete(String name) {
        if (roomRepository.findById(name).isPresent()) {
            roomRepository.deleteById(name);
        } else {
            console.printErr("The room doesn't exist.");
        }
    }
}
