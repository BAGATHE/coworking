package com.itu.coworking.repository;
import com.itu.coworking.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByTelephone(String numero);
    Utilisateur findByEmail(String email);
    Boolean existsByTelephone(String telephone);
}