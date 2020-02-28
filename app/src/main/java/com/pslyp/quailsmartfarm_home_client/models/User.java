package com.pslyp.quailsmartfarm_home_client.models;

public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private String picture;

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String id, String username, String email, String password, String picture) {
        this(id, username, email, password);
        this.picture = picture;
    }

    public String getId() {
        return id;
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
