package com.example.harjoitustyo2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Luokkalla määritellään yhden päivän makroravinteiden tietoja (proteiini, rasvat, sokeri, hiilihydraatit ja
 * kalorit).
 * @author Severi Salminen
 * @version 1.0 2024/04/24
 */


public class makroravinteet implements Serializable {
    /**
     *   päivämäärä pvm LocalDate muodossa
     */
    private LocalDate pvm;
    /**
     * proteiinin määrä desimaalilukuna
     */
    private double proteiini;
    /**
     *  rasvojen määrä desimaalilukuna
     */
    private double rasvat;
    /**
     *  sokerien määrä desimaalilukuna
     */
    private double sokerit;
    /**
     *  hiilihydraattien määrä desimaalilukuna
     */
    private double hiilihydraatit;
    /**
     *  kalorien määrä desimaalilukuna
     */
    private double kalorit;

    /**
     *  Palauttaa päivämäärän LocalDate muodossa
     * @return LocalDate
     */
    public LocalDate getPvm() {
        return pvm;
    }
    /**
     * Asettaa päivämäärän
     * @param pvm LocalDate
     */
    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }
    /**
     * Palauttaa proteiinin määrän
     * @return double
     */
    public double getProteiini() {
        return proteiini;
    }
    /**
     * Asettaa proteiinin määrän
     * @param proteiini double
     */
    public void setProteiini(double proteiini) {
        this.proteiini = proteiini;
    }
    /**
     * Palauttaa rasvojen määrän
     * @return double
     */
    public double getRasvat() {
        return rasvat;
    }
    /**
     * Asettaa proteiinin määrän
     * @param rasvat double
     */
    public void setRasvat(double rasvat) {
        this.rasvat = rasvat;
    }
    /**
     * Palauttaa sokerien määrän
     * @return double
     */
    public double getSokerit() {
        return sokerit;
    }
    /**
     * Asettaa proteiinin määrän
     * @param sokerit double
     */
    public void setSokerit(double sokerit) {
        this.sokerit = sokerit;
    }
    /**
     * Palauttaa hiilihydraattien määrän
     * @return double
     */
    public double getHiilihydraatit() {
        return hiilihydraatit;
    }
    /**
     * Asettaa proteiinin määrän
     * @param hiilihydraatit double
     */
    public void setHiilihydraatit(double hiilihydraatit) {
        this.hiilihydraatit = hiilihydraatit;
    }
    /**
     * Palauttaa kalorien määrän
     * @return double
     */
    public double getKalorit() {
        return kalorit;
    }
    /**
     * Asettaa proteiinin määrän
     * @param kalorit double
     */
    public void setKalorit(double kalorit) {
        this.kalorit = kalorit;
    }

    /**
     * Metodi palauttaa tiedot String muodossa
     * @return String
     */
    public String toString() {
        return "Päivämäärä: " + pvm.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +"\n" +
                "Kalorit: "+ String.format("%.0f", kalorit) + "\n" + "Proteiini: " + proteiini +
                " g \n" + "Rasvat: " + rasvat + " g \n" + "Sokerit: " + sokerit + "g \n" +
                "Hiilihydraatit: " + hiilihydraatit;
    }
}