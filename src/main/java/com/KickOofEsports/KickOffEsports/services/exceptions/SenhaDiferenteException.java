package com.KickOofEsports.KickOffEsports.services.exceptions;

public class SenhaDiferenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public SenhaDiferenteException() {
        super("Senha diferentes ");
    }
}
