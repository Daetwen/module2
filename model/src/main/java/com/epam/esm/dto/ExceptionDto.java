package com.epam.esm.dto;

public class ExceptionDto {

    private String errorMessage;
    private long errorCode;

    public ExceptionDto() {}

    public ExceptionDto(String errorMessage, long errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }
}
