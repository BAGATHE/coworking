package com.itu.coworking.repository;

import com.itu.coworking.dto.ReservationCountByHeure;
import com.itu.coworking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation,Integer> {
    Reservation findReservationByReference(String reference);

    @Query("SELECT r.heureDebut AS heureDebut, COUNT(r) AS count FROM Reservation r GROUP BY r.heureDebut")
    List<ReservationCountByHeure> countReservationsByHeureDebut();

    @Query("SELECT r FROM Reservation r WHERE r.dateReservation >= :dateDebut AND r.dateReservation <= :dateFin")
    List<Reservation> findReservationsBetweenDates(Date dateDebut, Date dateFin);
}
