package com.itu.coworking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "option_reservation")
public class OptionReservation {
    @EmbeddedId
    private OptionReservationId id;

    @ManyToOne
    @MapsId("idReservation")
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne
    @MapsId("idOption")
    @JoinColumn(name = "id_option")
    private Option option;
    public OptionReservation() {}
    public OptionReservation(Reservation reservation, Option option) {
        this.reservation = reservation;
        this.option = option;
        this.id = new OptionReservationId(reservation.getIdReservation(), option.getIdOption());
    }



}
