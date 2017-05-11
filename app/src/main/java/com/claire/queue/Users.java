package com.claire.queue;

public class Users {

    private String userUsername;
    private String userPhoneNumber;

    public Users(){

    }

    public Users(String userUsername, String userPhoneNumber) {
        this.userUsername = userUsername;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }
}
