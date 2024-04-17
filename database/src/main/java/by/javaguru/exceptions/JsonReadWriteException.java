package by.javaguru.exceptions;

import java.io.IOException;

public class JsonReadWriteException extends IOException {
    public JsonReadWriteException(String message) {
        super(message);
    }

    public JsonReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
