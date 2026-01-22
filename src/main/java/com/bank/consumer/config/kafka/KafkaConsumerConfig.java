package com.bank.consumer.config.kafka;

import com.bank.consumer.domain.event.TransferCreatedEvent;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * ConsumerFactory
     * As configurações de conexão, deserialização e segurança
     * são carregadas automaticamente do application.yml
     */
    @Bean
    public ConsumerFactory<String, TransferCreatedEvent> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties()
        );
    }

    /**
     * KafkaListenerContainerFactory
     * Define comportamento de execução do consumer
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransferCreatedEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TransferCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        // Commit manual (controle total do offset)
        factory.getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.MANUAL);

        // Processamento paralelo
        factory.setConcurrency(3);

        return factory;
    }
}
