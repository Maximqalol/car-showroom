package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.dto.DeliveryDto;
import ru.example.carshowroom.data.entity.Delivery;
import ru.example.carshowroom.data.mapper.DeliveryMapper;
import ru.example.carshowroom.data.repository.DeliveryRepository;
import ru.example.carshowroom.service.IDeliveryService;


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
    public DeliveryDto create(DeliveryDto deliveryDto) {
        log.debug("Mapping DeliveryDto to Delivery.");
        Delivery delivery = deliveryMapper.fromDto(deliveryDto);
        log.debug("Saving and returning delivery.");
        return deliveryMapper.toDto(deliveryRepository.save(delivery));
    }

    @Override
    public void remove(Integer deliveryId) {
        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public DeliveryDto getDeliveryByRequest(Integer requestId) {
        return deliveryMapper.toDto(deliveryRepository.findDeliveryByRequestId(requestId));
    }

    @Override
    public DeliveryDto getDeliveryById(Integer deliveryId) {
        return deliveryMapper.toDto(deliveryRepository.findDeliveryById(deliveryId));
    }

    @Override
    public List<DeliveryDto> getDeliveries() {
        return deliveryMapper.toListDto(deliveryRepository.findAll());
    }
}
