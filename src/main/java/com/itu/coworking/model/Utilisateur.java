package com.itu.coworking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "utilisateur")
public class Utilisateur {
@Id
@Column(name = "id_utilisateur")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idUtilisateur;

@Column(name = "telephone", unique = true,nullable = true,length = 10)
private  String telephone;

@Column(name="email",unique = true,nullable = true,length = 255)
private String email;

@Column(name = "motdepasse",nullable = true,length = 255)
private String motDePasse;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_profil",referencedColumnName = "id_profil",nullable = false)
private Profil profil;

    // Constructeurs
    public Utilisateur() {

    }
    public Utilisateur(String telephone, String mdp, String email, Profil profil) {
        this.telephone = telephone;
        this.motDePasse = mdp;
        this.email = email;
        this.profil = profil;
    }
    public Utilisateur(Integer idUtilisateur, String telephone, String mdp, String email, Profil profil) {
        this.idUtilisateur = idUtilisateur;
        this.telephone = telephone;
        this.motDePasse = mdp;
        this.email = email;
        this.profil = profil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(idUtilisateur, that.idUtilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", numero='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", profil=" + profil +
                '}';
    }
}
