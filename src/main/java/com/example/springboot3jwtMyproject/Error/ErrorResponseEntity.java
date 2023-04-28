package com.example.springboot3jwtMyproject.Error;

public class ErrorResponseEntity {
    private String errorMessage;
    private Boolean error;

//    private Map<String, String> additionalData;

    public ErrorResponseEntity(String errorMessage, Boolean error) {
        this.errorMessage = errorMessage;
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
