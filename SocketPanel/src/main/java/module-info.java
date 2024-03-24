module com.example.socketpanel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens com.example.socketpanel to javafx.fxml,com.google.gson;
    exports com.example.socketpanel;
}