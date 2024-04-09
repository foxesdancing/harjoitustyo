package com.example.harjoitustyo2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class makroravinteet implements Serializable {
    private LocalDate pvm;
    private double proteiini;
    private double rasvat;
    private double sokerit;
    private double hiilihydraatit;
    private double kalorit;

    public LocalDate getPvm() {
        return pvm;
    }
    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }
    public double getProteiini() {
        return proteiini;
    }
    public void setProteiini(double proteiini) {
        this.proteiini = proteiini;
    }
    public double getRasvat() {
        return rasvat;
    }
    public void setRasvat(double rasvat) {
        this.rasvat = rasvat;
    }
    public double getSokerit() {
        return sokerit;
    }
    public void setSokerit(double sokerit) {
        this.sokerit = sokerit;
    }
    public double getHiilihydraatit() {
        return hiilihydraatit;
    }
    public void setHiilihydraatit(double hiilihydraatit) {
        this.hiilihydraatit = hiilihydraatit;
    }
    public double getKalorit() {
        return kalorit;
    }
    public void setKalorit(double kalorit) {
        this.kalorit = kalorit;
    }
    public String toString() {
        return "Päivämäärä: " + pvm.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +"\n" +
                "Kalorit: "+ String.format("%.0f", kalorit) + "\n" + "Proteiini: " + proteiini +
                " g \n" + "Rasvat: " + rasvat + " g \n" + "Sokerit: " + sokerit + "g \n" +
                "Hiilihydraatit: " + hiilihydraatit;
    }
}

