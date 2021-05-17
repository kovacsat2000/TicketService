package com.epam.training.ticketservice.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import com.epam.training.ticketservice.user.UserServiceImpl;

@ShellComponent
public class MovieCommands {

    private final MovieServiceImpl movieServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public MovieCommands(MovieServiceImpl movieServiceImpl, UserServiceImpl userServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @ShellMethod(value = "List movies.", key = "list movies")
    public void listMovies() {
        movieServiceImpl.listAll();
    }

    @ShellMethod(value = "Create movie.", key = "create movie")
    public void create(String title, String genre, int length) {
        movieServiceImpl.create(title, genre, length);
    }

    @ShellMethod(value = "Update existing movie.", key = "update movie")
    public void update(String title, String genre, int length) {
        movieServiceImpl.update(title, genre, length);
    }

    @ShellMethod(value = "Delete a movie.", key = "delete movie")
    public void delete(String title) {
        movieServiceImpl.delete(title);
    }

    @ShellMethodAvailability({"create movie", "update movie", "delete movie"})
    public Availability isSignedIn() {
        return userServiceImpl.isSignedIn()
                ? Availability.available()
                : Availability.unavailable("you have to be signed in as a privileged user.");
    }
}
