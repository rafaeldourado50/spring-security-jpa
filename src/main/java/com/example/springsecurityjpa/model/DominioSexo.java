package com.example.springsecurityjpa.model;

public enum DominioSexo {

    M("M", "Masculino"),
    F("F", "Feminino"),
    O("O", "Outro");

    private String valor;
    private String descricao;

    DominioSexo(String valor, String descricao) {
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
