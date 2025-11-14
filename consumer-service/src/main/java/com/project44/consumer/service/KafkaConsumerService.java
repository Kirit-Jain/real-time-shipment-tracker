package com.project44.consumer.service;

import com.project44.consumer.dto.ShipmentUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);


    @KafkaListener(
        topics = "shipment-updates",
        groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleShipmentUpdate(ShipmentUpdate update)
    {
        LOGGER.info("RECEIVED SHIPMENT UPDATE: ");
        LOGGER.info(" Shipment ID: {}", update.shipmentId());
        LOGGER.info(" Location:    {}", update.location());
        LOGGER.info(" Timestamp:   {}", update.timestamp());
        LOGGER.info("------------------------------------");
    }
}
