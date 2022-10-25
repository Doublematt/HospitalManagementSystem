package com.example.hospitalmanagementsystem.Controllers;

import com.example.hospitalmanagementsystem.Database.PatientsConnection;
import com.example.hospitalmanagementsystem.Main;
import com.example.hospitalmanagementsystem.Pojo.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

public class addPatientController {

    @FXML
    private AnchorPane addPatientPane;

    @FXML
    private TextField ageField;

    @FXML
    private TextArea chronicDiseasesField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label goodLabel, badLabel;

    private PatientsConnection connection = new PatientsConnection();
    private Patient patient;


    public void addPatient(){
        System.out.println("does it word?!");

        int age = 0;

        try{
            age = Integer.parseInt(ageField.getText());
            System.out.println("integer parsed!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {

            patient = new Patient(
                    getRandomNumber(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    genderField.getText(),
                    age,
                    chronicDiseasesField.getText(),
                    emailField.getText());
            System.out.println("new Patient created!");
        }catch (NullPointerException e){
            System.out.println("creating patient error " + e.getMessage());
        }

        boolean succesful = connection.addPatient(patient);
        System.out.println("connection succ!");

        if(succesful){
            goodLabel.setVisible(true);
            badLabel.setVisible(false);
            System.out.println("patient ADDED!");

        }else{
            goodLabel.setVisible(false);
            badLabel.setVisible(true);
            System.out.println("error!");
        }

    }

    @FXML
    private void changeFXML (ActionEvent event) {

        FXMLLoader loader;
        Stage stage;
        Scene scene;

        try {
            loader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loader.load());
            DashboardController dashboardController = new DashboardController();
            dashboardController.setiD(1);

            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        } catch (Exception e) {
            System.out.println("Load new fxml error: " + e.getMessage());
        }
    }

    private Integer getRandomNumber(){
        Random random = new Random();
        int number = 0;
        int multiplayer = 10000;

        for(int i = 5; i > 0; i--){
            number += multiplayer * random.nextInt(10);
            multiplayer = multiplayer/10;
        }

    return number;
    }

}
