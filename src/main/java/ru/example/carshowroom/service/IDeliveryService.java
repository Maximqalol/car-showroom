package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    Delivery save(Delivery delivery);

    void remove(Integer deliveryId);

    Delivery getDeliveryByRequest(Integer requestId);

    Delivery getDeliveryById(Integer deliveryId);

    List<Delivery> getDeliveries();

}
