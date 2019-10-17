package com.pslyp.quailsmartfarm_home_client.models;

import java.util.Optional;

public class Response {

    private int status;
    private String message;
    private Optional optional;

    public Response(int status, String message, Optional optional) {
        this.status = status;
        this.message = message;
        this.optional = optional;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Optional getOptional() {
        return optional;
    }

}
