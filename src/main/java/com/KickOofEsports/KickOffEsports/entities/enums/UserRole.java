package com.KickOofEsports.KickOffEsports.entities.enums;

public enum UserRole {

    ADMIN("admin"),
    ESTOQUISTA("estoquista");

    private String funcao;
    UserRole(String grupo){this.funcao = grupo;}

    public String getRole(){
        return funcao;
    }
}
