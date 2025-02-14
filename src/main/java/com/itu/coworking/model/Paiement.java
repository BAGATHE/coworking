package com.itu.coworking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paiement")
    private Integer idPaiement;

    @Column(name = "ref_paiement",unique = true,nullable = false,length = 255)
    private String refPaiement;

    @Column(name = "date_paiement",nullable = false)
    private Date datePaiement;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    public Paiement() {}
    public Paiement(String refPaiement, Date datePaiement, Reservation reservation) {
        this.refPaiement = refPaiement;
        this.datePaiement = datePaiement;
        this.reservation = reservation;
    }

}
