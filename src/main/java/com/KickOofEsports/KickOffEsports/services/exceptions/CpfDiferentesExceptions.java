package com.KickOofEsports.KickOffEsports.services.exceptions;

public class CpfDiferentesExceptions extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CpfDiferentesExceptions() { super("Cpf não é alteravel"); }
}
