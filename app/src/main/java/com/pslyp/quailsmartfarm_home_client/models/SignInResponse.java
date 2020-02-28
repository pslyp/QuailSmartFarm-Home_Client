package com.pslyp.quailsmartfarm_home_client.models;


public class SignInResponse extends BaseResponse {

    private String id;
    private String username;
    private String token;

    public SignInResponse(int status, String id, String username, String token) {
        super(status);
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

}
