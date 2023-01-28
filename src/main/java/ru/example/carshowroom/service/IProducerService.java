package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Producer;

import java.util.List;

public interface IProducerService {

    Producer save(Producer producer);

    void remove(Integer producerId);

    Producer getProducerById(Integer producerId);

    List<Producer> getProducers();

}
