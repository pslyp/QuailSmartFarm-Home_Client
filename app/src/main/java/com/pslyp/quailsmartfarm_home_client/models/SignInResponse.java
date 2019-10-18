package com.pslyp.quailsmartfarm_home_client.models;

public class SignInResponse {

    private int status;
    private String message;
    private User user;

    public SignInResponse(int statuss, String message, User data) {
        this.status = statuss;
        this.message = message;
        this.user = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

}
