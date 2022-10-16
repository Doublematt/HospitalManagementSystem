package com.example.hospitalmanagementsystem.Controllers;


import com.example.hospitalmanagementsystem.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;


public class OperatingRoomController {

    @FXML
    private DatePicker room1Date;

    @FXML
    private Label room1Free;

    @FXML
    private AnchorPane room1ReservationPane;

    @FXML
    private Label room1Taken;

    @FXML
    private TextField room1Time;

    @FXML
    private DatePicker room2Date;

    @FXML
    private Label room2Free;

    @FXML
    private AnchorPane room2ReservationPane;

    @FXML
    private Label room2Taken;

    @FXML
    private TextField room2Time;

    @FXML
    private AnchorPane roomsPane;

    @FXML
    private Button room1Button, room2Button;



    public void goToReservation(javafx.event.ActionEvent event) {
        if (event.getSource() == room1Button){
            roomsPane.setVisible(false);
            room1ReservationPane.setVisible(true);
            room2ReservationPane.setVisible(false);
        }else  if (event.getSource() == room2Button){
            roomsPane.setVisible(false);
            room1ReservationPane.setVisible(false);
            room2ReservationPane.setVisible(true);
        }
    }

    public void goToDasboard (javafx.event.ActionEvent event){
        FXMLLoader loader;
        Stage stage;
        Scene scene;

        try{
            loader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loader.load());


            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        }catch (Exception e){
            System.out.println("Load new fxml error: " + e.getMessage());
        }

    }
}
