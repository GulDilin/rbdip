package ru.itmo.rbdip.exceptions;

public class InvalidUserCredentials extends Exception {
    public InvalidUserCredentials() {
        super(ErrorMessage.INVALID_CREDENTIAL);
    }
}
