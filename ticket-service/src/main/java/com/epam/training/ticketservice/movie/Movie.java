package com.epam.training.ticketservice.movie;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "movies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    String title;
    String genre;
    int length;

    public String toString() {
        return String.format("%s (%s, %d minutes)", this.title, this.genre, this.length);
    }
}
