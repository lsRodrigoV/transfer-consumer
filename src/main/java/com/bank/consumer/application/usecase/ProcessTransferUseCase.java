package com.bank.consumer.application.usecase;

import com.bank.consumer.domain.event.TransferCreatedEvent;
import com.bank.consumer.domain.model.Transferencia;
import org.springframework.stereotype.Service;

@Service
public class ProcessTransferUseCase {

    public void execute(TransferCreatedEvent event) { //“Processar uma transferência recebida via evento”

        Transferencia transferencia = new Transferencia(
                event.transferId(),
                event.valor(),
                event.contaOrigem(),
                event.contaDestino()
        );


        System.out.println("Transferência processada:");
        System.out.println("ID: " + transferencia.getId());
        System.out.println("Valor: " + transferencia.getValor());
        System.out.println("Origem: " + transferencia.getContaOrigem());
        System.out.println("Destino: " + transferencia.getContaDestino());
    }
}
