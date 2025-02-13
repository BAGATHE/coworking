package com.itu.coworking.repository;

import com.itu.coworking.model.Espace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, Integer> {
    Espace findByNom(String nom);
}
