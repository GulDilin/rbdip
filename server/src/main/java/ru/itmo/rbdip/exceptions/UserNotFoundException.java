package ru.itmo.rbdip.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
