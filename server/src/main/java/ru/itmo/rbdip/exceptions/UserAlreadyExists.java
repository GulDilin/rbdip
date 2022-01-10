package ru.itmo.rbdip.exceptions;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists() {
        super(ErrorMessage.USER_ALREADY_EXISTS);
    }
}
