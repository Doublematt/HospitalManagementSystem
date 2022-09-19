module com.example.hospitalmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.hospitalmanagementsystem to javafx.fxml;
    exports com.example.hospitalmanagementsystem;
    exports com.example.hospitalmanagementsystem.Controllers;
    opens com.example.hospitalmanagementsystem.Controllers to javafx.fxml;
}