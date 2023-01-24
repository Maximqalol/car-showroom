package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.dto.ProducerDto;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.mapper.ProducerMapper;
import ru.example.carshowroom.data.repository.ProducerRepository;
import ru.example.carshowroom.service.IProducerService;


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
    public ProducerDto create(ProducerDto producerDto) {
        log.debug("Mapping ProducerDto to Producer.");
        Producer producer = producerMapper.fromDto(producerDto);
        log.debug("Saving and returning producer.");
        return producerMapper.toDto(producerRepository.save(producer));
    }

    @Override
    public void remove(Integer producerId) {
        producerRepository.deleteById(producerId);
    }

    @Override
    public List<ProducerDto> getProducers() {
        return producerMapper.toListDto(producerRepository.findAll(Sort.by("name")));
    }

    @Override
    public ProducerDto getProducerById(Integer producerId) {
        return producerMapper.toDto(producerRepository.findById(producerId).orElse(null));
    }

}
