package com.itu.coworking.repository;

import com.itu.coworking.model.StatusReservation;
import com.itu.coworking.model.StatusReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusReservationRepository extends JpaRepository<StatusReservation, StatusReservationId> {

    List<StatusReservation> findByReservation_IdReservation(Integer idReservation);
    List<StatusReservation> findByStatus_IdStatus(Integer idStatus);
}
