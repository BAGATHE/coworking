package com.itu.coworking.dto;

public class ChiffreAffaire {
    double payer;
    double nonPayer;

    public ChiffreAffaire(double payer, double nonPayer) {
        this.payer = payer;
        this.nonPayer = nonPayer;
    }
    public double getPayer() {
        return payer;
    }

    public double getNonPayer() {
        return nonPayer;
    }
}
