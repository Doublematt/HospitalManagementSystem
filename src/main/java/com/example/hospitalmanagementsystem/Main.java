package com.example.hospitalmanagementsystem;

import com.example.hospitalmanagementsystem.Controllers.DashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hospital Management system");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        //implementing the below exit function
        stage.setOnCloseRequest(e -> {
            e.consume();
            exit(stage);
        });
    }

    /*
        Function to exit the application when alt + F4 is pressed
        Creates alert to make sure the user wants to close app
        Exits the app
     */
    public void exit(Stage stage){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure? ");

        if(alert.showAndWait().get() == ButtonType.OK){
            DashboardController.stop();
            System.out.println("close now");
            stage.close();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}