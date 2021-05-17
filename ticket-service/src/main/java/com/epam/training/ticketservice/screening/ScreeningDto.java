package com.epam.training.ticketservice.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.room.Room;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDto {
    private Movie movie;
    private Room room;
    private LocalDateTime start;

    public String toString() {
        String formattedStartTime = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return String.format("%s (%s, %d minutes), screened in room %s, at %s",
                movie.getTitle(), movie.getGenre(), movie.getLength(), room.getName(), formattedStartTime);
    }
}
