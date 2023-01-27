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
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void create(ProducerDto producerDto) {
        log.debug("Mapping ProducerDto to Producer.");
        Producer producer = producerMapper.fromDto(producerDto);
        log.debug("Saving producer.");
        producerMapper.toDto(producerRepository.save(producer));
    }

    @Override
    public void remove(Integer producerId) {
        producerRepository.deleteById(producerId);
    }

    @Override
    public void update(ProducerDto producerDto) {
        log.debug("Finding producer with id = {}.", producerDto.getId());
        Producer producer = producerRepository.findById(producerDto.getId()).orElse(null);
        final var producerBuilder = ProducerDto.Builder.aProducerDto()
                .withId(Objects.requireNonNull(producer).getId())
                .withName(producerDto.getName())
                .withPhone(producerDto.getPhone())
                .withAddress(producerDto.getAddress());
        Producer updatingProducer = producerMapper.fromDto(producerBuilder.build());
        log.debug("Updating producer.");
        producerMapper.toDto(producerRepository.save(updatingProducer));
    }

    @Override
    public ProducerDto getProducerById(Integer producerId) {
        return producerMapper.toDto(producerRepository.findById(producerId).orElse(null));
    }

    @Override
    public List<ProducerDto> getProducers() {
        return producerRepository.findAll(Sort.by("name")).stream().map(producerMapper::toDto).collect(Collectors.toList());
    }

}
