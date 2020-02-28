package com.pslyp.quailsmartfarm_home_client.models;


public class BaseResponse {

    private int status;

    public BaseResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
