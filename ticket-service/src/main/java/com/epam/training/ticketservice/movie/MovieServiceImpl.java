package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final Console console;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, Console console) {
        this.movieRepository = movieRepository;
        this.console = console;
    }

    @Override
    public void listAll() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            console.printErr("There are no movies at the moment");
        } else {
            movies.stream()
                    .map(Movie::toString)
                    .forEach(console::print);
        }
    }

    @Override
    public void create(String title, String genre, int length) {
        if (movieRepository.findById(title).isPresent()) {
            console.printErr("This movie is already in our database.");
        } else {
            movieRepository.save(
                    Movie.builder()
                            .title(title)
                            .genre(genre)
                            .length(length)
                            .build());

        }
    }

    @Override
    public void update(String title, String genre, int length) {
        if (movieRepository.findById(title).isEmpty()) {
            console.printErr("There is no movie with title: %s", title);
        } else {
            movieRepository.save(
                    Movie.builder()
                            .title(title)
                            .genre(genre)
                            .length(length)
                            .build());

        }
    }

    @Override
    public void delete(String title) {
        if (movieRepository.findById(title).isPresent()) {
            movieRepository.deleteById(title);

        } else {
            console.printErr("The movie doesn't exist.");
        }
    }

    @Override
    public Collection<Movie> findByTitle(String title) {
        return this.movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
