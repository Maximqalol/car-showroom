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
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void create(DeliveryDto deliveryDto) {
        log.debug("Mapping DeliveryDto to Delivery.");
        Delivery delivery = deliveryMapper.fromDto(deliveryDto);
        log.debug("Saving delivery.");
        deliveryMapper.toDto(deliveryRepository.save(delivery));
    }

    @Override
    public void remove(Integer deliveryId) {
        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public void update(DeliveryDto deliveryDto) {
        log.debug("Finding delivery with id = {}.", deliveryDto.getId());
        Delivery delivery = deliveryRepository.findById(deliveryDto.getId()).orElse(null);
        final var deliveryBuilder = DeliveryDto.Builder.aDeliveryDto()
                .withId(Objects.requireNonNull(delivery).getId())
                .withDeliveryMethod(deliveryDto.getDeliveryMethod())
                .withDateOfDelivery(deliveryDto.getDateOfDelivery())
                .withRequestId(deliveryDto.getRequestId());
        Delivery updatingDelivery = deliveryMapper.fromDto(deliveryBuilder.build());
        log.debug("Updating delivery.");
        deliveryMapper.toDto(deliveryRepository.save(updatingDelivery));
    }

    @Override
    public DeliveryDto getDeliveryByRequest(Integer requestId) {
        return deliveryMapper.toDto(deliveryRepository.findDeliveryByRequestId(requestId));
    }

    @Override
    public DeliveryDto getDeliveryById(Integer deliveryId) {
        return deliveryMapper.toDto(deliveryRepository.findById(deliveryId).orElse(null));
    }

    @Override
    public List<DeliveryDto> getDeliveries() {
        return deliveryRepository.findAll().stream().map(deliveryMapper::toDto).collect(Collectors.toList());
    }
}
