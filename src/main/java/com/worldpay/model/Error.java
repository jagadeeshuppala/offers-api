package com.worldpay.model;


public class Error {
    private Integer statusCode;
    private String message;
    private String documentation;

    public Error(Integer statusCode, String message, String documentation){
        this.statusCode = statusCode;
        this.message = message;
        this.documentation = documentation;
    }

    public Error() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
