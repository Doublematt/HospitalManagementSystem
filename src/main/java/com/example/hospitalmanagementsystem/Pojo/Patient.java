package com.example.hospitalmanagementsystem.Pojo;

public class Patient {

    private Integer PersonalID;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String chronicDiseases;
    private String email;

    public Patient(Integer personalID, String firstName, String lastName, String gender, Integer age, String chronicDiseases, String email) {
        PersonalID = personalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.chronicDiseases = chronicDiseases;
        this.email = email;
    }
    public Patient(){

    }

    public Integer getPersonalID() {
        return PersonalID;
    }

    public void setPersonalID(Integer personalID) {
        PersonalID = personalID;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
