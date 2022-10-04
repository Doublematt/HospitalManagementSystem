package com.example.hospitalmanagementsystem.Database;

import com.example.hospitalmanagementsystem.Pojo.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class PatientsConnection {

    private String url = "jdbc:mysql://localhost:3306/hospitalsystem";
    private String user = "root";
    private String password = "toor";

    public LinkedList<Patient> getAllPatients (){

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        LinkedList<Patient> patientsList= new LinkedList<Patient>();
        Patient patient;

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * From patients");

            while(resultSet.next()){
                patient = new Patient(resultSet.getInt("PersonalID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("chDiseases"),
                        resultSet.getString("email"));

                patientsList.add(patient);
            }

        }catch (Exception e){
            System.out.println("Patients, getAllPatients error: " + e.getMessage());
        }


        return patientsList;

    }
}
