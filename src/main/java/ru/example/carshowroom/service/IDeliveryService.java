package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.DeliveryDto;

import java.util.List;

public interface IDeliveryService {

    void create(DeliveryDto deliveryDto);

    void remove(Integer deliveryId);

    void update(DeliveryDto deliveryDto);

    DeliveryDto getDeliveryByRequest(Integer requestId);

    DeliveryDto getDeliveryById(Integer deliveryId);

    List<DeliveryDto> getDeliveries();

}
