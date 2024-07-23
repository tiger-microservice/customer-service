package com.tiger.customer.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerCustomer {

    @KafkaListener(
            topics = {"${spring.kafka.topic-create-customer-event:create-customer-event}"},
            groupId = "notification-handler"
    )
    public void handlerCreateCustomer(@Payload String message,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("[handlerCreateCustomer] message {}", message);
        log.info("[handlerCreateCustomer] topic {}", topic);
        log.info("[handlerCreateCustomer] key {}", key);
        try {
        } catch (Exception e) {
            log.error("[handlerCreateCustomer] error {}", e.getMessage(), e);
        }
    }
}
