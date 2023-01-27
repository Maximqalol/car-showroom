package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.ProducerDto;

import java.util.List;

public interface IProducerService {

    void create(ProducerDto producerDto);

    void remove(Integer producerId);

    void update(ProducerDto producerDto);

    ProducerDto getProducerById(Integer producerId);

    List<ProducerDto> getProducers();

}
