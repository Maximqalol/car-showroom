package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.mapper.ProducerMapper;
import ru.example.carshowroom.data.repository.ProducerRepository;
import ru.example.carshowroom.service.IProducerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProducerServiceImpl implements IProducerService {

    private final Logger log = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository, ProducerMapper producerMapper) {
        this.producerRepository = producerRepository;
        this.producerMapper = producerMapper;
    }

    @Override
    public Producer create(Producer producer) {
        log.debug("Save producer");
        return producerRepository.save(producer);
    }

    @Override
    public void remove(Integer producerId) {
        log.debug("Delete producer with id = {}.", producerId);
        producerRepository.deleteById(producerId);
    }

    @Override
    public Producer update(Producer producer) {
        log.debug("Find producer with id = {}.", producer.getId());
        Producer updatingProducer = producerRepository.findById(producer.getId()).orElseThrow(() -> new EntityNotFoundException("Can`t find producer with id = " + producer.getId()));
        updatingProducer.setId(producer.getId());
        updatingProducer.setName(producer.getName());
        updatingProducer.setPhone(producer.getPhone());
        updatingProducer.setAddress(producer.getAddress());
        log.debug("Update producer.");
        return producerRepository.save(updatingProducer);
    }

    @Override
    public Producer getProducerById(Integer producerId) {
        log.debug("Find producer by id = {}.", producerId);
        return producerRepository.findById(producerId).orElseThrow(() -> new EntityNotFoundException("Can`t find producer with id = " + producerId));
    }

    @Override
    public List<Producer> getProducers() {
        log.debug("Find all producers.");
        return producerRepository.findAll(Sort.by("name"));
    }

}
