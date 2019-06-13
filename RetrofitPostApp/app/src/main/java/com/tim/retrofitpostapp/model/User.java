package com.tim.retrofitpostapp.model;

/**
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public class User {

    private String userEmail;
    private String password;
    private int id;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
