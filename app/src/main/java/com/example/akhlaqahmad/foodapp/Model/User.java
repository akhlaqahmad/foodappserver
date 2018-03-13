package com.example.akhlaqahmad.foodapp.Model;

/**
 * Created by Akhlaq Ahmad on 1/25/2018.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String IsAdmin;


    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
        IsAdmin = "false";

    }

    public String getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        IsAdmin = isAdmin;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
