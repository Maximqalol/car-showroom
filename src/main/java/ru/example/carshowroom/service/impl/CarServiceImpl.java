package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.mapper.CarMapper;
import ru.example.carshowroom.data.repository.CarRepository;
import ru.example.carshowroom.service.ICarService;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {
    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarDto create(CarDto carDto) {
        log.debug("Mapping CarDto to Car.");
        Car car = carMapper.fromDto(carDto);
        log.debug("Saving and returning car.");
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public CarDto findCarById(Integer carId) {
        return carMapper.toDto(carRepository.findCarById(carId));
    }

    @Override
    public void remove(Integer carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carMapper.toListDto(carRepository.findAvailableCars());
    }

    @Override
    public List<CarDto> getAllCars() {
        return carMapper.toListDto(carRepository.findAll());
    }

}
