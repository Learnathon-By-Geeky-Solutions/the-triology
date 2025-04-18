package com.smart.health.care.management.system.response;

public class CustomResponse<T> {
    private String responseCode;
    private String responseMessage;
    private T data;

    public CustomResponse(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public T getData() {
        return data;
    }
}
