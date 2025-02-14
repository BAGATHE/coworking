package com.itu.coworking.repository;

import com.itu.coworking.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository  extends JpaRepository<Paiement,Integer> {

}
