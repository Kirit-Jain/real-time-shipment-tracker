package com.project44.producer.service;

import com.project44.producer.dto.ShipmentUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "shipment-updates";

    @Autowired
    private KafkaTemplate<String, ShipmentUpdate> kafkaTemplate;

    
    public void sendMessage(ShipmentUpdate update) {
        kafkaTemplate.send(TOPIC, update.shipmentId(), update)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    LOGGER.info("Sent message=[{}] with offset=[{}]", 
                        update, result.getRecordMetadata().offset());
                } else {
                    LOGGER.error("Unable to send message=[{}] due to : {}", 
                        update, ex.getMessage());
                }
            });
    }
}