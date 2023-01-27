package ru.example.carshowroom.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.DeliveryDto;
import ru.example.carshowroom.service.IDeliveryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/delivery",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DeliveryController {

    private final Logger log = LoggerFactory.getLogger(DeliveryController.class);

    private final IDeliveryService deliveryService;

    @Autowired
    public DeliveryController(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public void createDelivery(@RequestBody DeliveryDto deliveryDto) {
        log.info("Delivery to create: {}.", deliveryDto.toString());
        deliveryService.create(deliveryDto);
    }


    @DeleteMapping("/{deliveryId}")
    public void deleteDelivery(@PathVariable Integer deliveryId) {
        log.info("Remove delivery with id = {}.", deliveryId);
        deliveryService.remove(deliveryId);
    }

    @PutMapping
    public void updateDelivery(@RequestBody DeliveryDto deliveryDto) {
        log.info("Delivery to update: {}", deliveryDto.toString());
        deliveryService.update(deliveryDto);
    }

    @GetMapping("/{deliveryId}")
    public DeliveryDto getDeliveryById(@PathVariable Integer deliveryId) {
        log.info("Find delivery with id = {}.", deliveryId);
        return deliveryService.getDeliveryById(deliveryId);
    }

    @GetMapping("/request/{requestId}")
    public DeliveryDto getDeliveryByRequest(@PathVariable Integer requestId) {
        log.info("Find delivery with requestId = {}.", requestId);
        return deliveryService.getDeliveryByRequest(requestId);
    }

    @GetMapping("/all")
    public List<DeliveryDto> getAllDeliveries() {
        log.info("Return list of deliveries.");
        return deliveryService.getDeliveries();
    }
}