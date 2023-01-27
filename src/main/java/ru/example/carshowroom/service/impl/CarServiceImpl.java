package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.mapper.CarMapper;
import ru.example.carshowroom.data.repository.CarRepository;
import ru.example.carshowroom.service.ICarService;

import javax.persistence.EntityNotFoundException;
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
    public Car create(Car car) {
        log.debug("Save car.");
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        log.debug("Find car by id = {}.", car.getId());
        Car updatingCar = carRepository.findById(car.getId()).orElseThrow(() -> new EntityNotFoundException("Can't find car with id = " + car.getId()));
        updatingCar.setId(car.getId());
        updatingCar.setBrand(car.getModel());
        updatingCar.setModel(car.getModel());
        updatingCar.setFeature(car.getFeatures());
        updatingCar.setYear(car.getYear());
        updatingCar.setPrice(car.getPrice());
        updatingCar.setQuantity(car.getQuantity());
        updatingCar.setProducer(car.getProducer());
        log.debug("Update car.");
        return carRepository.save(updatingCar);
    }

    @Override
    public void remove(Integer carId) {
        log.debug("Delete car with id = {}", carId);
        carRepository.deleteById(carId);
    }

    @Override
    public Car findCarById(Integer carId) {
        log.debug("Find car by id = {}", carId);
        return carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Can't find car with id = " + carId));
    }

    @Override
    public List<Car> getAvailableCars() {
        log.debug("Find available cars.");
        return carRepository.findAvailableCars();
    }

    @Override
    public List<Car> getAllCars() {
        log.debug("Find all cars.");
        return carRepository.findAll();
    }

}
