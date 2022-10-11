package com.example.hospitalmanagementsystem.Controllers;

import com.example.hospitalmanagementsystem.Database.UsersConnection;
import com.example.hospitalmanagementsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.util.HashMap;

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

    @FXML
    private TextField logLoginField;

    @FXML
    private PasswordField logPasswordField;

    //no FXML variable
    private Alert alert;
    private Stage stage;
    private UsersConnection connection = new UsersConnection();
    private HashMap<String, String> usersMap = new HashMap<>();

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
    /*
        function operating on the hyperlinks
        both links are valid 28.09.2022
        method open browser and uses one of the links from below
     */
    public void openLink (ActionEvent event) throws URISyntaxException, IOException {
        if (event.getSource() == githubLink){
            System.out.println("Oppening the github link");
            Desktop.getDesktop().browse(new URI("https://github.com/Doublematt"));
        }else if (event.getSource() == LinkedinLink){
            System.out.println("Oppening the Linkedin link");
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/mateusz-matusewicz-a8783b233/"));
        }
    }
    /*
        contains database connection method which returns hashMap of User's logins and passwords
        first is not empty check
        second check proves that key - login equals to value - password
        for now there is only print, It will change after adding new fxml file.
     */

    public void login (ActionEvent e){

        usersMap = connection.getUserToLogin();
        if (logLoginField.getText().equals("") || logPasswordField.getText().equals("")) {
            System.out.println("one field is empty!");

        }else if (usersMap.get(logLoginField.getText()).equals(logPasswordField.getText())) {
                // log User in
                changeFXML(e);
                System.out.println("logging in");

            } else {
                System.out.println("failed!");
        }
    }

    //need to add some validation to User
    // possibly create new User pojo class,  may be useful
    /*
        array of User data is temporary answer, need to  find something better
        validation check only if fields are not empty
        only if they are not empty if adds new User to database
     */
    public void registerNewUser (){

        String [] user = {regEmailField.getText(), regLoginField.getText(), regPasswordField.getText(), regFirstNameField.getText(), regLastNameField.getText()};

        System.out.println("check not empty returns: " + checkNotEmptyRegister(user));
        if (checkNotEmptyRegister(user)){
            connection.registerNewUser(user[0], user[1], user[2], user[3], user[4]);
        }else{
            System.out.println("one or more fields are empty! ");
        }



    }

    //validation method - not empty register fields
    private boolean checkNotEmptyRegister (String [] fields){
        for (String field : fields){
            if (field.equals(""))
                return false;
        }
        return true;
    }

    private void changeFXML (ActionEvent event){

        FXMLLoader loader;
        Stage stage;
        Scene scene;

        try{
            loader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loader.load());
            DashboardController dashboardController = new DashboardController();
            System.out.println("usersID = " + connection.getUserID(logLoginField.getText(), logPasswordField.getText()));
            dashboardController.setiD(connection.getUserID(logLoginField.getText(), logPasswordField.getText()));

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