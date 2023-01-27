package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Producer;

import java.util.List;

public interface IProducerService {

    Producer create(Producer producer);

    void remove(Integer producerId);

    Producer update(Producer producer);

    Producer getProducerById(Integer producerId);

    List<Producer> getProducers();

}
