package com.itu.coworking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reservation")
    private Integer idReservation;

    @Column(name = "reference",unique = true,nullable = false,length = 255)
    private String reference;

    @Column(name = "date_reservation",nullable = false)
    private Date dateReservation;

    @Column(name = "heure_debut",nullable = false)
    private Time heureDebut;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur",referencedColumnName = "id_utilisateur",nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espace",referencedColumnName = "id_espace",nullable = false)
    private Espace espace;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<StatusReservation> statusReservations;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<OptionReservation> optionReservations;

    public Reservation() {}
    public Reservation(String reference, Date dateReservation, Time heureDebut, Integer duree, double total, Utilisateur utilisateur, Espace espace) {
        this.reference = reference;
        this.dateReservation = dateReservation;
        this.heureDebut = heureDebut;
        this.duree = duree;
        this.total = total;
        this.utilisateur = utilisateur;
        this.espace = espace;
    }
    public Reservation(Integer idReservation, String reference, Date dateReservation, Time heureDebut, Integer duree, double total, Utilisateur utilisateur, Espace espace) {
        this.idReservation = idReservation;
        this.reference = reference;
        this.dateReservation = dateReservation;
        this.heureDebut = heureDebut;
        this.duree = duree;
    }

}
