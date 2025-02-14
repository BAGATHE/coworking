package com.itu.coworking.repository;

import com.itu.coworking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository  extends JpaRepository<Reservation,Integer> {
    Reservation findReservationByReference(String reference);
}
