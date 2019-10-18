package com.pslyp.quailsmartfarm_home_client.models;

public class User {

    private String username;
    private String email;
    private String password;
    private String picture;

    public User(String username, String email, String password, String picture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPicture() {
        return picture;
    }

}
