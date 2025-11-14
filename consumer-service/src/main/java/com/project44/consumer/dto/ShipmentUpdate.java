package com.project44.consumer.dto;

public record ShipmentUpdate (
    String shipmentId,
    String location,
    long timestamp
) {
    
}
