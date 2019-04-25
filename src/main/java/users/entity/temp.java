package users.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "mainUsers")
public class mainUser {
    @Id
    String login;

    String LastName;
    String SurName;
    String DOB;
    String Address;
    ArrayList role;

    public String getLogin() {
        return login;
    }

    public mainUser(String login, String lastName, String surName, String DOB, String address, ArrayList role) {
        this.login = login;
        LastName = lastName;
        SurName = surName;
        this.DOB = DOB;
        Address = address;
        this.role = role;
    }

    public mainUser() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ArrayList getRole() {
        return role;
    }

    public void setRole(ArrayList role) {
        this.role = role;
    }
}
