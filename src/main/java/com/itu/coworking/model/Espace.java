package com.itu.coworking.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_espace")
    private Integer idEspace;

    @Column(name ="nom",unique = false,nullable = false,length = 255)
    private String nom;

    @Min(value = 0, message = "Le prix par heure doit être supérieur à 0.")
    @Column(name = "prix_heure", nullable = false)
    private double prixHeure;

    public Espace() {}

    public Espace(String nom, double prixHeure) {
        this.nom = nom;
        this.prixHeure = prixHeure;
    }
    public Espace(Integer idEspace, String nom, double prixHeure) {
        this.idEspace = idEspace;
        this.nom = nom;
        this.prixHeure = prixHeure;
    }
}
