package com.epam.training.ticketservice.movie;

public interface MovieService {

    void listAll();

    void create(String title, String genre, int length);

    void update(String title, String genre, int length);

    void delete(String title);

}
