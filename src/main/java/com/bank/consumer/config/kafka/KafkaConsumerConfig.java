package com.bank.consumer.config.kafka;

import com.bank.consumer.domain.event.TransferCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /**
     * ConsumerFactory padrão
     * Configura a desserialização JSON para eventos
     */
    @Bean
    public ConsumerFactory<String, TransferCreatedEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());

        // Garantias corporativas
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // commit manual
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // ler do início se não tiver offset
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.bank.transfer.domain.event"); // pacote seguro

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(TransferCreatedEvent.class, false)
        );
    }

    /**
     * ListenerContainerFactory
     * Controla comportamento do KafkaListener
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransferCreatedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransferCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Commit manual
        factory.getContainerProperties().setAckMode(
                org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL
        );

        // Opcional: processar mensagens em paralelo
        factory.setConcurrency(3);

        return factory;
    }
}
