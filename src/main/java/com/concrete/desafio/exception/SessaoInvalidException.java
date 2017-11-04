package com.concrete.desafio.exception;

public class SessaoInvalidException extends RuntimeException {
	public SessaoInvalidException() {
        super();
    }
    public SessaoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    public SessaoInvalidException(String message) {
        super(message);
    }
    public SessaoInvalidException(Throwable cause) {
        super(cause);
    }
}
