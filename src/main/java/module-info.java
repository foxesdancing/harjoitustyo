module com.example.harjoitustyo2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harjoitustyo2 to javafx.fxml;
    exports com.example.harjoitustyo2;
}