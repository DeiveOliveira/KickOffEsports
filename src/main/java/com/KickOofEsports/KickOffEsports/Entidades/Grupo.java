package com.KickOofEsports.KickOffEsports.Entidades;

public enum Grupo {

    ADMIN("admin"),
    ESTOQUISTA("estoquista");

    private String funcao;
    Grupo(String grupo){this.funcao = grupo;}

    public String getFuncao(){
        return funcao;
    }
}
