package ru.cft.focusstart.kartashev;

class ApplicationException extends Exception {

    ApplicationException(String message) {
        super(message);
    }

    ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
