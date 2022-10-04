package com.example.hospitalmanagementsystem.Controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



import java.awt.*;

import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.ResourceBundle;
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


    public  Thread thread;
    private static boolean processing = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
}
