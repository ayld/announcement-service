package com.tide.web.exception;

public class RequestParamException extends RuntimeException{

    public RequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParamException(String message) {
        super(message);
    }
}
