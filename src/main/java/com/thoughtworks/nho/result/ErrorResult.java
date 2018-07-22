package com.thoughtworks.nho.result;

public class ErrorResult {

    private int errorCode;

    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorResult{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public static ErrorResult build(int errorCode,String errorMessage) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.errorCode = errorCode;
        errorResult.errorMessage = errorMessage;
        return errorResult;
    }

}
