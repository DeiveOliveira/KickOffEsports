package com.KickOofEsports.KickOffEsports.services.exceptions;

public class EmailDiferentesException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailDiferentesException() { super("Email não é alteravel"); }
}