package com.example.hospitalmanagementsystem.Database;


import java.util.HashMap;
import java.sql.*;

public class UsersConnection {


    private String url = "jdbc:mysql://localhost:3306/hospitalsystem";
    private String user = "root";
    private String password = "toor";

    public HashMap<String, String> getUserToLogin (){

        Connection connection;
        Statement statement;
        ResultSet resultSet;
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



}
