package com.bank.consumer.config;

import com.bank.consumer.application.service.TransferConsumerService;
import com.bank.consumer.domain.event.TransferCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransferKafkaListener {

    private final TransferConsumerService service;

    public TransferKafkaListener(TransferConsumerService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "transfer-created",
            groupId = "transfer-consumer-group"
    )
    public void listen(TransferCreatedEvent event) {
        System.out.println("Evento recebido do Kafka");
        service.process(event);
    }
}
