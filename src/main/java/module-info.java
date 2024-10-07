module com.example.colormemory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.colormemory to javafx.fxml;
    exports com.example.colormemory;
}