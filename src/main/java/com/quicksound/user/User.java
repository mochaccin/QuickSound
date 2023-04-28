package com.quicksound.user;

public class User {
    private String name;
    private String password;
    private int id;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public void updateUserData(String userName, String userPassword){
        setUserName(userName);
        setUserPassword(userPassword);
    }

}