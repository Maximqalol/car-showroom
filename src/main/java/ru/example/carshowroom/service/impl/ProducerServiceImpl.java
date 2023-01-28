package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.repository.ProducerRepository;
import ru.example.carshowroom.service.IProducerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProducerServiceImpl implements IProducerService {

    private final Logger log = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer save(Producer producer) {
        log.debug("Save producer");
        return producerRepository.save(producer);
    }

    @Override
    public void remove(Integer producerId) {
        log.debug("Delete producer with id = {}.", producerId);
        producerRepository.deleteById(producerId);
    }

    @Override
    public Producer getProducerById(Integer producerId) {
        log.debug("Find producer by id = {}.", producerId);
        return producerRepository.findById(producerId).orElseThrow(() -> new EntityNotFoundException("Can't find producer with id = " + producerId));
    }

    @Override
    public List<Producer> getProducers() {
        log.debug("Find all producers.");
        return producerRepository.findAll(Sort.by("name"));
    }

}
