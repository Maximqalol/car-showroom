package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.ProducerDto;
import ru.example.carshowroom.service.IProducerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/producer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProducerController {

    private final Logger log = LoggerFactory.getLogger(ProducerController.class);

    private final IProducerService producerService;

    @Autowired
    public ProducerController(IProducerService producerService) {
        this.producerService = producerService;
    }


    @PostMapping
    public void createProducer(@RequestBody ProducerDto producerDto) {
        log.info("Producer to create: {}.", producerDto.toString());
        producerService.create(producerDto);
    }

    @DeleteMapping("/{producerId}")
    public void deleteProducer(@PathVariable Integer producerId) {
        log.info("Remove producer with id = {}.", producerId);
        producerService.remove(producerId);
    }

    @PutMapping
    public void updateProducer(@RequestBody ProducerDto producerDto) {
        log.info("Producer to update: {}", producerDto);
        producerService.update(producerDto);
    }

    @GetMapping("/{producerId}")
    public ProducerDto getProducerById(@PathVariable Integer producerId) {
        log.info("Return producer with id = {}.", producerId);
        return producerService.getProducerById(producerId);
    }

    @GetMapping("/all")
    public List<ProducerDto> getAllProducers() {
        log.info("Return list of producers.");
        return producerService.getProducers();
    }
}
