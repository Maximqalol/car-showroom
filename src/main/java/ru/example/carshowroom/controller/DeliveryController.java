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
    public DeliveryDto createDelivery(@RequestBody DeliveryDto deliveryDto) {
        log.info("Delivery to create {}.", deliveryDto.toString());
        return deliveryService.create(deliveryDto);
    }


    @DeleteMapping("/{deliveryId}")
    public void deleteDelivery(@PathVariable Integer deliveryId) {
        if (deliveryService.getDeliveryById(deliveryId) == null) {
            log.error("Can't delete delivery with id = {}", deliveryId);
        } else {
            deliveryService.remove(deliveryId);
            log.info("Delivery with id = {} was deleted.", deliveryId);
        }
    }

    @GetMapping("/{deliveryId}")
    public DeliveryDto getDeliveryById(@PathVariable Integer deliveryId) {
        DeliveryDto deliveryDto = deliveryService.getDeliveryById(deliveryId);
        if (deliveryDto != null) {
            log.info("Founded delivery with id = {} and requestId = {}.", deliveryDto.getId(), deliveryDto.getRequestId());
            return deliveryDto;
        } else {
            log.error("Can't find delivery with id = {}", deliveryId);
        }
        return null;
    }

    @GetMapping("/request/{requestId}")
    public DeliveryDto getDeliveryByRequest(@PathVariable Integer requestId) {
        DeliveryDto deliveryDto = deliveryService.getDeliveryByRequest(requestId);
        if (deliveryDto != null) {
            log.info("Founded delivery with id = {} and requestId = {}.", deliveryDto.getId(), deliveryDto.getRequestId());
            return deliveryDto;
        } else {
            log.error("Can't find delivery with requestId = {}", requestId);
        }
        return null;
    }

    @GetMapping("/all")
    public List<DeliveryDto> getAllDeliveries() {
        List<DeliveryDto> deliveryList = deliveryService.getDeliveries();
        if (deliveryList.isEmpty()) {
            log.warn("List of deliveries is empty.");
        } else {
            log.info("Returned list of deliveries with size = {}.", deliveryList.size());
        }
        return deliveryList;
    }
}