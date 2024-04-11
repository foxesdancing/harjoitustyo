package com.example.harjoitustyo2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/** Luokka hoitaa päivittäisen makroravinteiden seuraamiseen tarkoitetun ohjelman tiedostoon
 * tallenus ominausuuden, joka muuttaa tallennettavat tiedot sopivaan muotoon tiedostoon.
 * @author Severi Salminen
 * @version 1.0
 */

public class makroravinteetTiedosto {
    /**
     * Päivämäärän muotoilunmuuttaja.
     */
    private static final DateTimeFormatter pvmMuotoilu = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // päivämäärän muotoilu MM isolla koska pienellä tarkoittaa minuutteja
    /**
     * Metodi joka tallentaa makroravinteiden tiedot tiedostoon sopivassa muodossa.
     * @param tiedosto              Tiedosto johon tiedot tallennetaan
     * @param pvmS                  Päivämäärä merkkijonona muodossa "dd.MM.yyyy"
     * @param proteiiniS            Proteiinin määrä merkkijonona
     * @param rasvatS               Rasvojen määrä merkkijonona
     * @param sokeritS              Sokerien määrä merkkijonona
     * @param hiilihydraatitS       Hiilihydraattien määrä merkkijonona
     * @param kaloritS              Kalorien määrä merkkijonona
     */
    public static void tallennaTiedostoon(File tiedosto, String pvmS, String proteiiniS, String rasvatS, String sokeritS,
                                          String hiilihydraatitS, String kaloritS) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(tiedosto, true))) {
            writer.println(pvmS + "," + proteiiniS + "," + rasvatS + "," + sokeritS + "," + hiilihydraatitS + "," + kaloritS);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa");
        }
    }
    /**
     * Metodi joka lataa tiedot tiedostosta ja palauttaa makroravinteet listan
     * @param tiedosto      Tiedosto johon tiedot tallennetaan
     * @return makroravinteetLista
     */
    public static ArrayList<makroravinteet> lataaTiedostosta(File tiedosto) {
        ArrayList<makroravinteet> makroravinteetLista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    LocalDate parsedPvm = LocalDate.parse(parts[0], pvmMuotoilu);
                    makroravinteet tiedot = new makroravinteet();
                    tiedot.setPvm(parsedPvm);
                    tiedot.setProteiini(Double.parseDouble(parts[1]));
                    tiedot.setRasvat(Double.parseDouble(parts[2]));
                    tiedot.setSokerit(Double.parseDouble(parts[3]));
                    tiedot.setHiilihydraatit(Double.parseDouble(parts[4]));
                    tiedot.setKalorit(Double.parseDouble(parts[5]));
                    makroravinteetLista.add(tiedot);
                }
            }
        } catch (IOException e) {

        } catch (DateTimeParseException | NumberFormatException e) {
            // Jos päivämäärän tai makroravinteiden määrän muunnos epäonnistuu, tulostetaan virheviesti
            System.err.println("Virheellinen muoto tiedostossa:" + e.getMessage());
        }
        return makroravinteetLista;
    }
}