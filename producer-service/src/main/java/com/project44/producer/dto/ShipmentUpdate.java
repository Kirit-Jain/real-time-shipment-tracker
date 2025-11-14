package com.project44.producer.dto;

public record ShipmentUpdate(
    String shipmentId,
    String location,
    long timestamp
) {

}
