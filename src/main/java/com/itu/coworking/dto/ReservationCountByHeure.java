package com.itu.coworking.dto;

import java.time.LocalTime;

public interface ReservationCountByHeure {
    LocalTime getHeureDebut();
    Long getCount();
}
