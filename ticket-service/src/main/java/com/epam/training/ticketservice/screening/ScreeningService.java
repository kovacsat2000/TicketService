package com.epam.training.ticketservice.screening;

import java.util.NoSuchElementException;

public interface ScreeningService {
    void listAll();

    void create(String movieTitle, String roomName, String start);

    void delete(String movieTitle, String roomName, String start);

    ScreeningDto convertToScreeningDto(Screening screening);

    ScreeningDto convertToScreeningDto(String movieTitle, String roomName, String start) throws NoSuchElementException;
}
