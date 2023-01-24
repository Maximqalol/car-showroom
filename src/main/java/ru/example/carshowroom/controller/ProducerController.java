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
    public ProducerDto createProducer(@RequestBody ProducerDto producerDto) {
        log.info("Producer to create {}.", producerDto.toString());
        return producerService.create(producerDto);
    }

    @DeleteMapping("/{producerId}")
    public void deleteProducer(@PathVariable Integer producerId) {
        if (producerService.getProducerById(producerId) == null) {
            log.error("Can't delete producer with id = {}", producerId);
        } else {
            producerService.remove(producerId);
            log.info("Producer with id = {} was deleted.", producerId);
        }
    }

    @GetMapping("/all")
    public List<ProducerDto> getAllProducers() {
        List<ProducerDto> producerList = producerService.getProducers();
        if (producerList.isEmpty()) {
            log.warn("List of producers is empty.");
        } else {
            log.info("Returned list of producers with size = {}.", producerList.size());
        }
        return producerList;
    }
}
