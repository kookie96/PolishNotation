module com.example.polishnotation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.polishnotation to javafx.fxml;
    exports com.example.polishnotation;
}