package com.project44.producer.controller;

import com.project44.producer.dto.ShipmentUpdate;
import com.project44.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {
    @Autowired
    private KafkaProducerService producerService;


    @PostMapping("/update")
    public ResponseEntity<String> publicUpdate(@RequestBody ShipmentUpdate update)
    {
        ShipmentUpdate updateWithTimestamp = new ShipmentUpdate(
            update.shipmentId(),
            update.location(),
            System.currentTimeMillis()
        );

        producerService.sendMessage(updateWithTimestamp);
        return ResponseEntity.ok("Shipment update received and published");
    }
}
