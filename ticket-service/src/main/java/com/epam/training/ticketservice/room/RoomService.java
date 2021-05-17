package com.epam.training.ticketservice.room;

import java.util.Collection;

public interface RoomService {
    void listAll();

    void create(String name, int rows, int columns);

    void update(String name, int rows, int columns);

    void delete(String name);

    Collection<Room> findByName(String name);
}
