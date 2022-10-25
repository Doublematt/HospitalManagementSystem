package com.example.hospitalmanagementsystem.Database;

import com.example.hospitalmanagementsystem.Pojo.Patient;

import java.io.IOException;
import java.sql.*;
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

    public LinkedList<Patient> getPatients( String firstName, String lastName, String gender,
                                           String age, String chronicDiseases, String email){

        LinkedList<Patient> patientLinkedList = new LinkedList<>();
        Patient patient;



        Connection connection;
        Statement statement;
        ResultSet resultSet;
        String sql  = "Select * From patients where ";
        sql += createQuestion(firstName, lastName, gender, age, chronicDiseases, email);
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                patient = new Patient(resultSet.getInt("PersonalID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("chDiseases"),
                        resultSet.getString("email")
                        );
                patientLinkedList.add(patient);
            }

        }catch (Exception e){
            System.out.println("getPatients error: " + e.getMessage() );
        }

        return patientLinkedList;

    }

    public String createQuestion ( String firstName, String lastName, String gender,
                                   String age, String chronicDiseases, String email){
        String question = "";

        if (!firstName.equals("")){
            question += "firstName =" + " '" + firstName + "'";
        }if (!lastName.equals(""))
            question += " and "+ "lastName = "+ " '" + lastName + "'";
        if (!age.equals(""))
            question += " and age = " + age;
        if (!gender.equals(""))
            question += " and "+ "gender = '" + gender + "'";
        if (!chronicDiseases.equals(""))
            question += " and "+ " chDiseases = '" + chronicDiseases + "'";
        if (!email.equals(""))
            question += " and "+ "email = '" + email + "'";

        if(question.startsWith(" and"))
            question = question.substring(4);

        System.out.println("question: " + question);

        return question;
    }

    public LinkedList<Patient> getPatientByID (Integer ID){
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        LinkedList<Patient> patientLinkedList = new LinkedList<Patient>();
        Patient patient = new Patient();

        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM patients where personalID = " + ID);

            while(resultSet.next()){
                patient = new Patient(ID,
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getString("chDiseases"),
                        resultSet.getString("email"));
                patientLinkedList.add(patient);
            }

        }catch (Exception e){
            System.out.println("getPatientByID error: " + e.getMessage());
        }
        return patientLinkedList;
    }

    public boolean addPatient(Patient patient){

        Connection connection;
        PreparedStatement statement;
        try {


            connection = DriverManager.getConnection(url, user, password);

            statement = connection.prepareStatement("Insert into patients " +
                    "(firstName, lastName, gender, age, chDiseases, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getGender());
            statement.setInt(4, patient.getAge());
            statement.setString(5, patient.getChronicDiseases());
            statement.setString(6, patient.getEmail());

            statement.executeQuery();
            System.out.println("query done!");
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
