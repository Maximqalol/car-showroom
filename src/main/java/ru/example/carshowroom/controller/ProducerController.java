package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.ProducerDto;
import ru.example.carshowroom.data.mapper.ProducerMapper;
import ru.example.carshowroom.service.IProducerService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/producer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProducerController {

    private final Logger log = LoggerFactory.getLogger(ProducerController.class);
    private final ProducerMapper producerMapper;
    private final IProducerService producerService;

    @Autowired
    public ProducerController(ProducerMapper producerMapper, IProducerService producerService) {
        this.producerMapper = producerMapper;
        this.producerService = producerService;
    }


    @PostMapping
    public void createProducer(@RequestBody ProducerDto producerDto) {
        log.info("Producer to create: {}.", producerDto.toString());
        producerService.save(producerMapper.fromDto(producerDto));
    }

    @DeleteMapping("/{producerId}")
    public void deleteProducer(@PathVariable Integer producerId) {
        log.info("Remove producer with id = {}.", producerId);
        producerService.remove(producerId);
    }

    @PutMapping("/{producerId}")
    public void updateProducer(@RequestBody ProducerDto producerDto) {
        log.info("Producer to update: {}", producerDto);
        producerService.save(producerMapper.fromDto(producerDto));
    }

    @GetMapping("/{producerId}")
    public ProducerDto getProducerById(@PathVariable Integer producerId) {
        log.info("Return producer with id = {}.", producerId);
        return producerMapper.toDto(producerService.getProducerById(producerId));
    }

    @GetMapping("/all")
    public List<ProducerDto> getAllProducers() {
        log.info("Return list of producers.");
        return producerService.getProducers().stream().map(producerMapper::toDto).collect(Collectors.toList());
    }
}
