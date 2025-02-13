package com.itu.coworking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name="id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatus;

    @Column(name="status",unique = true,nullable = false,length = 255)
    private String status;

    @Column(name="code",unique = true,nullable = false,length = 3)
    private String code;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<StatusReservation> statusReservations;


    public Status() {}
    public Status(String status, String code) {
        this.status = status;
        this.code = code;
    }
    public Status(Integer idStatus, String status, String code) {
        this.idStatus = idStatus;
        this.status = status;
        this.code = code;
    }



}
