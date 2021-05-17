package com.epam.training.ticketservice.screening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, ScreeningID> {
    List<Screening> findAll();
}
