package org.rizki.mufrizal.baseline.exception;

public class ParameterNotFoundException extends Exception {
    public ParameterNotFoundException(String message) {
        super(message);
    }

    public ParameterNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
