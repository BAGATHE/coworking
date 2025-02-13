package com.itu.coworking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "status_reservation")
public class StatusReservation {
    @EmbeddedId
    private StatusReservationId id;

    @ManyToOne
    @MapsId("idReservation")
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne
    @MapsId("idStatus")
    @JoinColumn(name = "id_status")
    private Status status;

    @Column(name = "date_status", nullable = false)
    private LocalDateTime dateStatus;

    public StatusReservation() {}

    public StatusReservation(Reservation reservation, Status status, LocalDateTime dateStatus) {
        this.reservation = reservation;
        this.status = status;
        this.dateStatus = dateStatus;
        this.id = new StatusReservationId(reservation.getIdReservation(), status.getIdStatus());
    }
}
