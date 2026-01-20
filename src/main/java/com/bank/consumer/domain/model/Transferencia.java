package com.bank.consumer.domain.model;

public class Transferencia {

    private final String id;
    private final Double valor;
    private final String contaOrigem;
    private final String contaDestino;

    public Transferencia(String id, Double valor, String contaOrigem, String contaDestino) {
        this.id = id;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public String getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }
}
