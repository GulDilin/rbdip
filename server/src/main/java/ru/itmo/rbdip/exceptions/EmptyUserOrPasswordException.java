package ru.itmo.rbdip.exceptions;

public class EmptyUserOrPasswordException extends Exception {
    public EmptyUserOrPasswordException() {
        super(ErrorMessage.USER_OR_PASSWORD_IS_EMPTY);
    }
}
