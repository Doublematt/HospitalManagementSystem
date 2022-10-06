package com.example.hospitalmanagementsystem.Database;

import com.example.hospitalmanagementsystem.Pojo.StuffMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class StuffConnection {

    private String url = "jdbc:mysql://localhost:3306/hospitalsystem";
    private String user = "root";
    private String password = "toor";

    public LinkedList<StuffMember> getAllStuffMembers (){

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        LinkedList<StuffMember> stuff = new LinkedList<>();
        StuffMember member;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * From stuff;");

            while (resultSet.next()){
                member = new StuffMember(
                        resultSet.getInt("stuffID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getString("occupation")
                );
                stuff.add(member);
            }

        }catch (Exception e){
            System.out.println("GetAllStuff error: " + e.getMessage());
        }

        return stuff;
    }

}
