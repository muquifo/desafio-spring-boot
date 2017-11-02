package com.concrete.desafio.exception;

public class TokenInvalidException extends RuntimeException {
	public TokenInvalidException() {
        super();
    }
    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    public TokenInvalidException(String message) {
        super(message);
    }
    public TokenInvalidException(Throwable cause) {
        super(cause);
    }
}
