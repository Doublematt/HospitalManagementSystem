package com.example.hospitalmanagementsystem.Controllers;


import com.example.hospitalmanagementsystem.Database.PatientsConnection;
import com.example.hospitalmanagementsystem.Database.StuffConnection;
import com.example.hospitalmanagementsystem.Pojo.Patient;
import com.example.hospitalmanagementsystem.Pojo.StuffMember;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class DashboardController  implements Initializable {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button patientsButton;

    @FXML
    private Button stuffButton;

    @FXML
    private AnchorPane menuPane, rootPane, dashboardPane, patientPane, stuffPane;

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
    private TableView<StuffMember> stuffTable;

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

    @FXML
    private TableColumn<StuffMember, Integer> stuffIDcolumn;

    @FXML
    private TableColumn<StuffMember, String> sfirstNameColumn;

    @FXML
    private TableColumn<StuffMember, String> slastNameColumn;

    @FXML
    private TableColumn<StuffMember, String> sgenderColumn;

    @FXML
    private TableColumn<StuffMember, String> sOccupationColumn;


    private PatientsConnection patientsConnection = new PatientsConnection();
    private StuffConnection stuffConnection = new StuffConnection();
    public  Thread thread;
    private static boolean processing = true;
    private LinkedList<Patient> patientList;
    private ObservableList<Patient> patientObservableList;
    private LinkedList<StuffMember> stuffList;
    private ObservableList<StuffMember> stuffObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllStudents();
        getAllStuffMembers();
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Do you want to exit?");
        alert.setContentText("Are you sure?");
        alert.setHeaderText("");

        if(alert.showAndWait().get() == ButtonType.OK){
            stop();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }
    }



    public void changePane(javafx.event.ActionEvent event) {
        if(event.getSource() == dashboardButton){
            patientPane.setVisible(false);
            dashboardPane.setVisible(true);
            stuffPane.setVisible(false);

        } else if (event.getSource() == patientsButton){
            patientPane.setVisible(true);
            dashboardPane.setVisible(false);
            stuffPane.setVisible(false);
        }else if (event.getSource() == stuffButton){
            stuffPane.setVisible(true);
            dashboardPane.setVisible(false);
            patientPane.setVisible(false);
        }
    }

    public void getAllStudents (){
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
    }

    public void getAllStuffMembers(){
        try {
            stuffList = stuffConnection.getAllStuffMembers();
            stuffObservableList = FXCollections.observableArrayList();
            stuffObservableList.addAll(stuffList);
        }catch (Exception e){
            System.out.println("list error: " + e.getMessage());
        }

        try {
            stuffTable.setItems(stuffObservableList);

            stuffIDcolumn.setCellValueFactory(new PropertyValueFactory<StuffMember, Integer>("stuffID"));
            sfirstNameColumn.setCellValueFactory(new PropertyValueFactory<StuffMember, String >("firstName"));
            slastNameColumn.setCellValueFactory(new PropertyValueFactory<StuffMember, String>("lastName"));
            sgenderColumn.setCellValueFactory(new PropertyValueFactory<StuffMember, String>("gender"));
            sOccupationColumn.setCellValueFactory(new PropertyValueFactory<StuffMember, String >("occupation"));

        }catch (Exception e){
            System.out.println("Stuff table creating exception " + e.getMessage());
        }
    }
}
