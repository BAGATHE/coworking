package com.itu.coworking.repository;

import com.itu.coworking.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil,Integer> {
    boolean existsByProfil(String profil);
    Profil findByProfil(String profil);
}
