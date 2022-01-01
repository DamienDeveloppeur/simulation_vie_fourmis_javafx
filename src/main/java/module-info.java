module ch.epfl.moocprog.gfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.antfx to javafx.fxml;
    exports ch.epfl.moocprog.gfx;
}