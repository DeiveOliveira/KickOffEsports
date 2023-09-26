package com.KickOofEsports.KickOffEsports.services.exceptions;

public class LoginInvalidadoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoginInvalidadoException() { super("Erro ao tentar longar"); }
}

