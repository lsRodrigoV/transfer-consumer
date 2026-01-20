package com.bank.consumer.domain.event;

public record TransferCreatedEvent(
        String transferId,
        Double valor,
        String contaOrigem,
        String contaDestino
) {}
