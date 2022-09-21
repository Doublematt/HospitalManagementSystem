package com.example.hospitalmanagementsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class LoginController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Hyperlink LinkedinLink;

    @FXML
    private Hyperlink createAccountLink;

    @FXML
    private AnchorPane createAccountPane;

    @FXML
    private Hyperlink githubLink;

    @FXML
    private Button logSubmitButton;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField regConfirmPasswordField;

    @FXML
    private TextField regEmailField;

    @FXML
    private TextField regFirstNameField;

    @FXML
    private TextField regLastNameField;

    @FXML
    private TextField regLoginField;

    @FXML
    private PasswordField regPasswordField;

    //no FXML variable
    private Alert alert;
    private Stage stage;

    /*
    Function to change between login and register
    Connected to login hyperlink and create account hyperlink
     */

    public void changePane (ActionEvent e){
        if(e.getSource() == loginLink){
            loginPane.setVisible(true);
            createAccountPane.setVisible(false);
        }else if(e.getSource() == createAccountLink){
            loginPane.setVisible(false);
            createAccountPane.setVisible(true);
        }
    }

    /*
    Function connected to exit button
    Exits the app
     */
    public void exit(ActionEvent event){

        //alert instance
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure? ");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) mainPane.getScene().getWindow();
            System.out.println("exit now");
            stage.close();

        }

    }

    public void openLink (ActionEvent event) throws URISyntaxException, IOException {
        if (event.getSource() == githubLink){
            System.out.println("Oppening the github link");
            Desktop.getDesktop().browse(new URI("https://github.com/Doublematt"));
        }else if (event.getSource() == LinkedinLink){
            System.out.println("Oppening the Linkedin link");
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/mateusz-matusewicz-a8783b233/"));
        }
    }

}