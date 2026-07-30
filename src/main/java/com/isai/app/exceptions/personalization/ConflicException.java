package com.isai.app.exceptions.personalization;

public class ConflicException extends
        RuntimeException {
    private static final long serialVersionUID = 1;

    public ConflicException(String message) {
        super(message);
    }
}
