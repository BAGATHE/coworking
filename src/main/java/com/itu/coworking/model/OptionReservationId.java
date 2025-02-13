package com.itu.coworking.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OptionReservationId implements java.io.Serializable {
    private Integer idReservation;
    private Integer idOption;
}
