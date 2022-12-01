module com.example.formulario {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.formulario to javafx.fxml;
    exports com.example.formulario;
}