/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javachips.studentinformationsystem;

/**
 *
 * @author psg420
 */
public class User {
    private int id;
    private String username, password;
    private boolean isFaculty;

    public User(String username, String password, boolean isFaculty) {
        this.username = username;
        this.password = password;
        this.isFaculty = isFaculty;
    }

    public User(int id, String username, String password, boolean isFaculty) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isFaculty = isFaculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFaculty() {
        return isFaculty;
    }

    public boolean getFaculty() {
        return isFaculty;
    }

    public void setFaculty(boolean faculty) {
        isFaculty = faculty;
    }

}
