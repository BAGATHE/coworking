package com.itu.coworking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_option")
    private Integer idOption;

    @Column(name="code",unique = true,nullable = false,length = 255)
    private String code;

    @Column(name="option",unique = false,nullable = false,length = 255)
    private String option;

    @Column(name="prix",nullable = false)
    @Min(value = 0,message = "prix doit etre superieur a 0")
    private double prix;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<OptionReservation> optionReservations;

    public Option() {}

    public Option(String code, String option, double prix) {
        this.code = code;
        this.option = option;
        this.prix = prix;
    }
    public Option(Integer idOption, String code, String option, double prix) {
        this.idOption = idOption;
        this.code = code;
        this.option = option;
        this.prix = prix;
    }
}
