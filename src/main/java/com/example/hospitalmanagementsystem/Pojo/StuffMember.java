package com.example.hospitalmanagementsystem.Pojo;

public class StuffMember {

    private Integer stuffID;
    private String firstName;
    private String lastName;
    private String gender;
    private String occupation;

    public StuffMember(Integer stuffID, String firstName, String lastName, String gender, String occupation) {
        this.stuffID = stuffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.occupation = occupation;
    }

    public Integer getStuffID() {
        return stuffID;
    }

    public void setStuffID(Integer stuffID) {
        this.stuffID = stuffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
