package com.epam.training.ticketservice.screening;

import com.epam.training.ticketservice.room.Room;
import com.epam.training.ticketservice.movie.Movie;
import com.epam.training.ticketservice.screening.ScreeningID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@Entity
@Table(name = "screenings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screening {

    @EmbeddedId
    private ScreeningID id;

    @ManyToOne
    @MapsId("movieTitle")
    private Movie movie;

    @ManyToOne
    @MapsId("roomName")
    private Room room;
}
