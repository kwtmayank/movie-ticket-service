package com.mtb.model;

public class Response<T> {
    private String errorCode;
    private String message;
    private boolean success;

    private T data;

    public Response(String errorCode, String message, boolean success, T data) {
        this.errorCode = errorCode;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
