package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Delivery;

import java.util.List;

public interface IDeliveryService {

    Delivery create(Delivery delivery);

    void remove(Integer deliveryId);

    Delivery update(Delivery delivery);

    Delivery getDeliveryByRequest(Integer requestId);

    Delivery getDeliveryById(Integer deliveryId);

    List<Delivery> getDeliveries();

}
