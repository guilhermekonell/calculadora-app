package com.example.aula_27_09;

public class Calculo {
    private String operacao;
    private String resultado;

    public Calculo(String operacao, String resultado) {
        this.operacao = operacao;
        this.resultado = resultado;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getResultado() {
        return resultado;
    }
}