/**
 *  Projekti joka antaa käyttäjän tallentaa tietyn päivän makroravinteensa tiedostoon
 */
module com.example.harjoitustyo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    opens com.example.harjoitustyo2 to javafx.fxml;
    exports com.example.harjoitustyo2;
}