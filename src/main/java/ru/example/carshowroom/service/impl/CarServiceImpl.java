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
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void create(CarDto carDto) {
        log.debug("Mapping CarDto to Car.");
        Car car = carMapper.fromDto(carDto);
        log.debug("Saving car.");
        carMapper.toDto(carRepository.save(car));
    }

    @Override
    public CarDto findCarById(Integer carId) {
        return carMapper.toDto(carRepository.findById(carId).orElse(null));
    }

    @Override
    public void update(CarDto carDto) {
        log.debug("Finding car with id = {}.", carDto.getId());
        Car car = carRepository.findById(carDto.getId()).orElse(null);
        final var carBuilder = CarDto.Builder.aCarDto()
                .withId(Objects.requireNonNull(car).getId())
                .withBrand(carDto.getBrand())
                .withModel(carDto.getModel())
                .withFeatures(carDto.getFeatures())
                .withYear(carDto.getYear())
                .withPrice(carDto.getPrice())
                .withQuantity(carDto.getQuantity())
                .withProducerId(carDto.getProducerId());
        Car updatingCar = carMapper.fromDto(carBuilder.build());
        log.debug("Updating car.");
        carMapper.toDto(carRepository.save(updatingCar));
    }

    @Override
    public void remove(Integer carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carRepository.findAvailableCars().stream().map(carMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::toDto).collect(Collectors.toList());
    }

}
