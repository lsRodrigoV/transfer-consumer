package com.bank.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication(scanBasePackages = "com.bank.consumer")
public class TransferConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferConsumerApplication.class, args);
    }
}
