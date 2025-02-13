package com.itu.coworking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profil")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profil")
    private  Integer idProfil;

    @Column(name="profil",unique = true,nullable = false,length = 30)
    private String profil;

    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurs;

    public Profil() {
    }

    public Profil(String profil) {
        this.profil = profil;
    }
    public Profil(Integer idProfil, String profil) {
        this.idProfil = idProfil;
        this.profil = profil;
    }

    // Getters et Setters
    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "idProfil=" + idProfil +
                ", profil='" + profil + '\'' +
                '}';
    }
}