package com.example.hospitalmanagementsystem.Controllers;

import com.example.hospitalmanagementsystem.Database.PatientsConnection;
import com.example.hospitalmanagementsystem.Database.UsersConnection;
import com.example.hospitalmanagementsystem.Main;
import com.example.hospitalmanagementsystem.Pojo.Patient;
import com.example.hospitalmanagementsystem.Pojo.StuffMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private TextField personalIDField, firstNameField, lastNameField, genderField, ageField, emailField, chDieseasesField;

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

    @FXML
    private AnchorPane findPatientPane, addPatientPane;
    //non FXML variables
    PatientsConnection patientsConnection = new PatientsConnection();
    LinkedList<Patient> patientList;
    ObservableList<Patient> patientObservableList;
    static boolean pane = true;
    private UsersConnection connection = new UsersConnection();


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

    public void getPatients(){

        TextField [] fields = {personalIDField, firstNameField, lastNameField, genderField, ageField, chDieseasesField, emailField};

        patientObservableList = FXCollections.observableArrayList();

        if(!personalIDField.getText().equals("")){
            //implement getPatient by ID
            System.out.println("by ID");
            Integer ID = 0;
            try {
                ID = Integer.parseInt(personalIDField.getText());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            patientList = patientsConnection.getPatientByID(ID);

            patientObservableList.addAll(patientList);

        }else if(checkNoEmpty(fields)){
            patientList = patientsConnection.getAllPatients();
            patientObservableList.addAll(patientList);
        } else {


            patientList = patientsConnection.getPatients(
                firstNameField.getText(),
                lastNameField.getText(),
                genderField.getText(),
                ageField.getText(),
                chDieseasesField.getText(),
                emailField.getText());

            patientObservableList.addAll(patientList);

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

    private boolean checkNoEmpty (TextField [] fields){

        boolean emptiness = true;

        for (TextField field : fields){
            if (field.getText().equals(""))
                emptiness = emptiness && true;
            else{
                emptiness = emptiness && false;
            }

        }
        System.out.println("emptiness: " + emptiness);
        return emptiness;
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
}
