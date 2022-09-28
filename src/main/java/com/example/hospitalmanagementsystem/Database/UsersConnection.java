package com.example.hospitalmanagementsystem.Database;


import java.util.HashMap;
import java.sql.*;

public class UsersConnection {


    private String url = "jdbc:mysql://localhost:3306/hospitalsystem";
    private String user = "root";
    private String password = "toor";

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    /*
        Select type connection
        used for login validation
        returns hashmap of every user in the database and his password
     */

    public HashMap<String, String> getUserToLogin (){


        HashMap<String, String> person = new HashMap<>();

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select login, password from users");

            while (resultSet.next()){

                person.put(resultSet.getString("login"), resultSet.getString("password"));
                System.out.println("data: " + resultSet.getString("login") + ", " + resultSet.getString("password"));

            }



        }catch (Exception e){
            System.out.println("get User to Login data error: " + e.getMessage());
        }

        return person;

    }
    /*
        Insert type connection
        used in register form to create new user
        do not contain validation!
        prints are for debugging
        maybe should specify the exception and add finally form
     */
    public void registerNewUser (String email, String userLogin, String userPassword, String firstName, String lastName){

        try {

            System.out.println("Connecting...");
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement("Insert into users " +
                    "(email, login, password, firstName, lastName) " +
                    "VALUES (?, ?, ?, ?, ? )"
                    );

            System.out.println("Creating statement...");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, userLogin);
            preparedStatement.setString(3, userPassword);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);

            System.out.println("updating database...");
            preparedStatement.executeUpdate();

            System.out.println("successful!");

        }catch (Exception e){
            System.out.println("add new user error: " + e.getMessage());
        }




    }



}
