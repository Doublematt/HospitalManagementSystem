package com.example.hospitalmanagementsystem.Controllers;


import com.example.hospitalmanagementsystem.Database.PatientsConnection;
import com.example.hospitalmanagementsystem.Pojo.Patient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



import java.awt.*;

import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.scene.control.Button;




public class DashboardController  implements Initializable {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button patientsButton;

    @FXML
    private AnchorPane menuPane, rootPane, dashboardPane, patientPane;

    @FXML
    private ImageView newsImage1;

    @FXML
    private ImageView newsImage2;

    @FXML
    private ImageView newsImage3;

    @FXML
    private AnchorPane newsPane;

    @FXML
    private Label nameLabel;


    @FXML
    private TableView<Patient> patientsTable;

    @FXML
    private TableColumn<Patient, Integer> personalIDColumn;

    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TableColumn<Patient, String> chDiseasesColumn;

    @FXML
    private TableColumn<Patient, String> emailColumn;

    @FXML
    private TableColumn<Patient, String> firstNameColumn;

    @FXML
    private TableColumn<Patient, String> genderColumn;

    @FXML
    private TableColumn<Patient, String> lastNameColumn;


    private PatientsConnection patientsConnection = new PatientsConnection();
    public  Thread thread;
    private static boolean processing = true;
    private LinkedList<Patient> patientList;
    private ObservableList<Patient> patientObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            patientList = patientsConnection.getAllPatients();
            patientObservableList = FXCollections.observableArrayList();
            patientObservableList.addAll(patientList);
        }catch (Exception e){
            System.out.println("list error: " + e.getMessage());
        }

        try {
            patientsTable.setItems(patientObservableList);

            personalIDColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("PersonalID"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
            genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
            chDiseasesColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("chronicDiseases"));
        }catch (Exception e){
            System.out.println("Table creating exception " + e.getMessage());
        }
        getTime();

    }

    public void getTime (){
            thread = new Thread(() -> {
               SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
               while(processing){
                   try {
                       Thread.sleep(1000);
                   }catch (Exception e){
                       System.out.println(e.getMessage());
                   }
                   final String timenow = sdf.format(new Date());
                   Platform.runLater(() -> {
                       nameLabel.setText(timenow);
                   });
               }
            });
            thread.start();
        }


    public static void stop(){
        processing = false;
    }

    public void exit (){
        stop();
    }



    public void changePane(javafx.event.ActionEvent event) {
        if(event.getSource() == dashboardButton){
            patientPane.setVisible(false);
            dashboardPane.setVisible(true);
        } else if (event.getSource() == patientsButton){
            patientPane.setVisible(true);
            dashboardPane.setVisible(false);
        }
    }

    public void getAllStudents (){
        patientList = patientsConnection.getAllPatients();
        patientObservableList = FXCollections.observableArrayList();
        patientObservableList.addAll(patientList);

        patientsTable.setItems(patientObservableList);

        personalIDColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("PersonalID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
        chDiseasesColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("chronicDiseases"));

    }
}
