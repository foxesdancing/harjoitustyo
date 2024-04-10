package com.example.harjoitustyo2;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *  Päivittäistä makroravinteiden seurantaa käsittelevän luokan käyttöliittymä, jossa käyttäjä voi tallentaa
 *  päivittäiset makroravinteidensa määrän haluttuna päivänä ja katsastella aiemmin tallennettuja päiviä ja
 *  näiden tietoja käyttöliittymästä.
 * @author Severi Salminen
 * @version 1.0
 */
public class makroravinteetGrafiikka extends Application {
    /**
     *  Listview jossa näytetään tallennetut päivämäärät
     */
    private ListView<String> pvmListView;
    /**
     *   textarea jossa näytetään valitun päivämäärän tiedot
     */
    private TextArea ravintoTekstiarea;
    /**
     *    Arraylist tallennetuille päivämäärille
     */
    private ObservableList<String> pvmLista = FXCollections.observableArrayList();
    /**
     *    Arraylist tallennetuille makroravinteille
     */
    private ArrayList<makroravinteet> makroravinteetLista;
    /**
     *    Päivämäärän muotoilija
     */
    private DateTimeFormatter pvmMuotoilu = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    /**
     *    Tiedosto johon kaikki tallennettavat tiedot tallennetaan
     */
    private File tiedosto = new File("makroravinteet_tallennettu.dat");

    /**
     *    Metodi joka tallentaa tiedot tiedostoon
     * @param pvmS
     * @param proteiiniS
     * @param rasvatS
     * @param sokeritS
     * @param hiilihydraatitS
     * @param kaloritS
     */
    private void tallennaTiedostoon(String pvmS, String proteiiniS, String rasvatS, String sokeritS,
                                    String hiilihydraatitS, String kaloritS) {
        try{
            LocalDate parsedPvm = LocalDate.parse(pvmS, pvmMuotoilu);

            if (!onkoNumero(proteiiniS)) {
                throw new IllegalArgumentException("Virhe: Proteiinin määrä on annettava numeroina.");
            }
            if (!onkoNumero(rasvatS)) {
                throw new IllegalArgumentException("Virhe: Rasvojen määrä on annettava numeroina.");
            }
            if (!onkoNumero(sokeritS)) {
                throw new IllegalArgumentException("Virhe: Sokerin määrä on annettava numeroina.");
            }
            if (!onkoNumero(hiilihydraatitS)) {
                throw new IllegalArgumentException("Virhe: Hiilihydraattien määrä on annettava numeroina.");
            }
            if (!onkoNumero(kaloritS)) {
                throw new IllegalArgumentException("Virhe: Kalorien määrä on annettava numeroina.");
            }

        makroravinteetTiedosto.tallennaTiedostoon(tiedosto, pvmS, proteiiniS, rasvatS, sokeritS, hiilihydraatitS, kaloritS);
        pvmLista.add(parsedPvm.format(pvmMuotoilu));
        makroravinteet tiedot = new makroravinteet();
        tiedot.setPvm(parsedPvm);
        tiedot.setProteiini(Double.parseDouble(proteiiniS));
        tiedot.setRasvat(Double.parseDouble(rasvatS));
        tiedot.setSokerit(Double.parseDouble(sokeritS));
        tiedot.setHiilihydraatit(Double.parseDouble(hiilihydraatitS));
        tiedot.setKalorit(Double.parseDouble(kaloritS));
        makroravinteetLista.add(tiedot);

    } catch (Exception e) {
            Alert virheIlmoitus = new Alert(Alert.AlertType.ERROR);
            virheIlmoitus.setTitle("Virhe syötteessä.");
            virheIlmoitus.setContentText(e.getMessage());
            virheIlmoitus.showAndWait();
        }
    }

    /**
     *  Metodi joka tarkistaa onko annettu merkkijono mahdollista muutta numeroiksi
     *  @param str tarkistettava merkkijono
     *  @return True, jos merkkijono on muutettavissa numeroiksi,
     *          False muussa tapauksessa
     */
    private boolean onkoNumero(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *  Lataa makroravinteiden tiedot tiedostosta ja lisää ladatun päivämäärän päivämäärä-listaan
     */
    private void lataaTiedostosta() {
        makroravinteetLista = makroravinteetTiedosto.lataaTiedostosta(tiedosto);
        for (makroravinteet tiedot : makroravinteetLista) {
            pvmLista.add(tiedot.getPvm().format(pvmMuotoilu));
        }
    }
    /**
     *  Ohjelmaikkunan käynnistyksen, toiminnallisuuden ja asettelun määrittely.
     */
    @Override
    public void start(Stage primaryStage){
        // teksti kentän asetukset
        ravintoTekstiarea = new TextArea();
        ravintoTekstiarea.setEditable(false);

        // Päivämäärä listview:n toiminta
        pvmListView = new ListView<>();
        pvmListView.setItems(pvmLista);
        pvmListView.setOnMouseClicked(event -> {
            int valittuPvm = pvmListView.getSelectionModel().getSelectedIndex();
            if (valittuPvm >= 0 && valittuPvm < makroravinteetLista.size()) {
                makroravinteet tiedot = makroravinteetLista.get(valittuPvm);
                ravintoTekstiarea.setText(tiedot.toString());
            }
        });

        // kentät ravintotietojen syöttämiselle
        TextField pvmKentta = new TextField();
        pvmKentta.setPromptText("Pvm (DD.MM.YYYY)");

        TextField proteiiniKentta = new TextField();
        proteiiniKentta.setPromptText("Proteiini (g)");

        TextField rasvatKentta = new TextField();
        rasvatKentta.setPromptText("Rasvat (g)");

        TextField sokeritKentta = new TextField();
        sokeritKentta.setPromptText("Sokerit (g)");

        TextField hiilihydKentta = new TextField();
        hiilihydKentta.setPromptText("Hiilihydraatit (g)");

        TextField kaloritKentta = new TextField();
        kaloritKentta.setPromptText("Kalorit");

        // button jolla tallennetaan tiedot
        Button tallennaButton = new Button("Tallenna");
        tallennaButton.setOnAction(event -> {
            tallennaTiedostoon(pvmKentta.getText(),proteiiniKentta.getText(),rasvatKentta.getText(),
                    sokeritKentta.getText(),hiilihydKentta.getText(),kaloritKentta.getText());
            pvmKentta.clear();
            proteiiniKentta.clear();
            rasvatKentta.clear();
            sokeritKentta.clear();
            hiilihydKentta.clear();
            kaloritKentta.clear();
        });

        //gridpane johon asetetaan ravintotieto kentät
        GridPane kentatPaneeli = new GridPane();
        kentatPaneeli.setHgap(15);
        kentatPaneeli.setHgap(15);

        // kenttien ja tallenna buttonin lisääminen
        kentatPaneeli.addRow(0, new Label("Päivämäärä:"),pvmKentta);
        kentatPaneeli.addRow(1,new Label("Proteiini:"),proteiiniKentta);
        kentatPaneeli.addRow(2,new Label("Rasvat:"),rasvatKentta);
        kentatPaneeli.addRow(3,new Label("Sokerit:"),sokeritKentta);
        kentatPaneeli.addRow(4,new Label("Hiilihydraatit:"),hiilihydKentta);
        kentatPaneeli.addRow(5,new Label("Kalorit:"),kaloritKentta);
        kentatPaneeli.addRow(6,tallennaButton);

        // vbox johon asetetaan viewlist päivämäärille ja kentatPaneeli
        VBox vbox = new VBox(10, pvmListView,kentatPaneeli);

        BorderPane paneeli = new BorderPane();
        paneeli.setLeft(vbox);
        paneeli.setCenter(ravintoTekstiarea);

        lataaTiedostosta();

        Scene scene = new Scene(paneeli, 800,500);
        primaryStage.setTitle("Makroravinteiden seuraamisohjelma");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

