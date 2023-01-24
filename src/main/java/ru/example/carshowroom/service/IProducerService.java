package ru.example.carshowroom.service;


import ru.example.carshowroom.data.dto.ProducerDto;

import java.util.List;

public interface IProducerService {

    ProducerDto create(ProducerDto producerDto);

    void remove(Integer producerId);

    List<ProducerDto> getProducers();

    ProducerDto getProducerById(Integer producerId);
}
