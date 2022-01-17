package ru.itmo.rbdip.exceptions;

public class InvalidAuthorizationHeader extends Exception {
    public InvalidAuthorizationHeader() {
        super(ErrorMessage.INVALID_AUTH_HEADER);
    }
}
