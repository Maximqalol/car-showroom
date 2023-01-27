package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.service.ICarService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/car",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CarController {

    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public void createCar(@RequestBody CarDto carDto) {
        log.info("Car to create: {}.", carDto.toString());
        carService.create(carDto);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable Integer carId) {
        log.info("Remove car with id = {}.", carId);
        carService.remove(carId);
    }

    @PutMapping
    public void updateCar(@RequestBody CarDto carDto) {
        log.info("Car to update: {}.", carDto.toString());
        carService.update(carDto);
    }

    @GetMapping("/{carId}")
    public CarDto getCarById(@PathVariable Integer carId) {
        log.info("Find car with id = {}.", carId);
        return carService.findCarById(carId);
    }

    @GetMapping("/all")
    public List<CarDto> getAllCars() {
        log.info("Return list of cars.");
        return carService.getAllCars();
    }

    @GetMapping("/all/available")
    public List<CarDto> getAllAvailableCars() {
        log.info("Return list of cars.");
        return carService.getAvailableCars();
    }

}
