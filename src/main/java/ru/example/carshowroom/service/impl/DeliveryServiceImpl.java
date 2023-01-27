package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Delivery;
import ru.example.carshowroom.data.mapper.DeliveryMapper;
import ru.example.carshowroom.data.repository.DeliveryRepository;
import ru.example.carshowroom.service.IDeliveryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    private final Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);
    private final DeliveryMapper deliveryMapper;

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryMapper deliveryMapper, DeliveryRepository deliveryRepository) {
        this.deliveryMapper = deliveryMapper;
        this.deliveryRepository = deliveryRepository;
    }


    @Override
    public Delivery create(Delivery delivery) {
        log.debug("Save delivery.");
        return deliveryRepository.save(delivery);
    }

    @Override
    public void remove(Integer deliveryId) {
        log.debug("Delete delivery with id = {}.", deliveryId);
        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public Delivery update(Delivery delivery) {
        log.debug("Find delivery by id = {}.", delivery.getId());
        Delivery updatingDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow(() -> new EntityNotFoundException("Can't find delivery with id = " + delivery.getId()));
        updatingDelivery.setId(delivery.getId());
        updatingDelivery.setDeliveryMethod(delivery.getDeliveryMethod());
        updatingDelivery.setDateOfDelivery(delivery.getDateOfDelivery());
        updatingDelivery.setRequest(delivery.getRequest());
        log.debug("Update delivery.");
        return deliveryRepository.save(updatingDelivery);
    }

    @Override
    public Delivery getDeliveryByRequest(Integer requestId) {
        log.debug("Find delivery by requestId = {}.", requestId);
        return deliveryRepository.findDeliveryByRequestId(requestId);
    }

    @Override
    public Delivery getDeliveryById(Integer deliveryId) {
        log.debug("Find delivery by id = {}.", deliveryId);
        return deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityNotFoundException("Can't find delivery with id = " + deliveryId));
    }

    @Override
    public List<Delivery> getDeliveries() {
        log.debug("Find all deliveries.");
        return deliveryRepository.findAll();
    }
}
