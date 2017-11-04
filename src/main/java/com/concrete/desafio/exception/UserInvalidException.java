package com.concrete.desafio.exception;

public class UserInvalidException extends RuntimeException {
	public UserInvalidException() {
        super();
    }
    public UserInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserInvalidException(String message) {
        super(message);
    }
    public UserInvalidException(Throwable cause) {
        super(cause);
    }
}
