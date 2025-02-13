package com.itu.coworking.repository;

import com.itu.coworking.model.OptionReservation;
import com.itu.coworking.model.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionReservationRepository  extends JpaRepository<OptionReservation,Integer> {
    List<OptionReservation> findByReservation_IdReservation(Integer idReservation);
    List<OptionReservation> findByOption_IdOption(Integer idOption);
}
