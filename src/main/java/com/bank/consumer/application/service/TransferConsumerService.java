package com.bank.consumer.application.service;

import com.bank.consumer.application.usecase.ProcessTransferUseCase;
import com.bank.consumer.domain.event.TransferCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class TransferConsumerService {

    private final ProcessTransferUseCase useCase;

    public TransferConsumerService(ProcessTransferUseCase useCase) {
        this.useCase = useCase;
    }

     // Ponto único de entrada para eventos de transferência

    public void process(TransferCreatedEvent event) {
        useCase.execute(event);
    }
}
