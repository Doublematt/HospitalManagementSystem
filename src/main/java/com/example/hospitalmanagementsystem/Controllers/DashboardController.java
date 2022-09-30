package com.example.hospitalmanagementsystem.Controllers;

import com.example.hospitalmanagementsystem.Main;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;


public class DashboardController  implements Initializable {

    @FXML
    private AnchorPane menuPane, rootPane;

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


}
