package com.claire.queue;

public class Users {

    private String userUsername;
    private String userPhoneNumber;
    private String userICNum;

    public Users(){

    }

    public Users(String userUsername, String userPhoneNumber, String userICNum) {
        this.userUsername = userUsername;
        this.userPhoneNumber = userPhoneNumber;
        this.userICNum = userICNum;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserICNum() {
        return userICNum;
    }
}
