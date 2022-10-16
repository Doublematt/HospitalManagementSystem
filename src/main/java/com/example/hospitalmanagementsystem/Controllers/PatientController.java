package com.example.hospitalmanagementsystem.Controllers;

import com.example.hospitalmanagementsystem.Database.PatientsConnection;
import com.example.hospitalmanagementsystem.Pojo.Patient;
import com.example.hospitalmanagementsystem.Pojo.StuffMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

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

    //non FXML variables
    PatientsConnection patientsConnection = new PatientsConnection();
    LinkedList<Patient> patientList;
    ObservableList<Patient> patientObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllPatients();

    }

    public void getAllPatients() {
        try {
            patientList = patientsConnection.getAllPatients();
            patientObservableList = FXCollections.observableArrayList();
            patientObservableList.addAll(patientList);
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Table creating exception " + e.getMessage());
        }
    }
}
