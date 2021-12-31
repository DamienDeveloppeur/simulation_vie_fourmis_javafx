module com.example.antfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.antfx to javafx.fxml;
    exports com.example.antfx;
}