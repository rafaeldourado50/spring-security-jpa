package com.example.springsecurityjpa.model;

public enum DominioSimNao {

    S("S", "Sim"),
    N("N", "Não");

    private String valor;
    private String descricao;

    DominioSimNao(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
