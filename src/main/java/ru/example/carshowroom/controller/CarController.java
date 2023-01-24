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
    public CarDto createCar(@RequestBody CarDto carDto) {
        log.info("Car to create {}.", carDto.toString());
        return carService.create(carDto);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable Integer carId) {
        if (carService.findCarById(carId) == null) {
            log.error("Can't delete car with id = {}", carId);
        } else {
            carService.remove(carId);
            log.info("Car with id = {} was deleted.", carId);
        }
    }

    @GetMapping("/{carId}")
    public CarDto getCarById(@PathVariable Integer carId) {
        CarDto carDto = carService.findCarById(carId);
        if (carDto != null) {
            log.info("Founded car with id = {} is {} {}.", carDto.getId(), carDto.getBrand(), carDto.getModel());
            return carDto;
        } else {
            log.error("Can't find car with id = {}", carId);
        }
        return null;
    }

    @GetMapping("/all")
    public List<CarDto> getAllCars() {
        List<CarDto> carlist = carService.getAllCars();
        if (carlist.isEmpty()) {
            log.warn("List of cars is empty.");
        } else {
            log.info("Returned list of cars with size = {}.", carlist.size());
        }
        return carlist;
    }

    @GetMapping("/all/available")
    public List<CarDto> getAllAvailableCars() {
        List<CarDto> carlist = carService.getAvailableCars();
        if (carlist.isEmpty()) {
            log.warn("List of cars is empty.");
        } else {
            log.info("Returned list of cars with size = {}.", carlist.size());
        }
        return carlist;
    }

}
