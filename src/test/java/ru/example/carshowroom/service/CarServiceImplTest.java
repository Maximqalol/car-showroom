package ru.example.carshowroom.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.repository.CarRepository;
import ru.example.carshowroom.service.impl.CarServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Test
    public void givenNewCar_whenCreate_thenSaved() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        carService.create(car);
        verify(carRepository).save(car);
    }

    @Test
    public void givenCar_whenDelete_thenDeleted() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        carService.remove(car.getId());
        verify(carRepository).deleteById(car.getId());
    }

    @Test
    public void givenCar_whenUpdate_thenUpdated() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        carService.update(car);
        verify(carRepository).save(car);
    }

    @Test
    public void givenCar_whenFindById_thenCarFound() {
        Optional<Car> expected = Optional.of(new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone")));
        when(carRepository.findById(1337)).thenReturn(expected);
        Car actual = carService.findCarById(1337);
        assertEquals(expected.get(), actual);
        verify(carRepository, atLeastOnce()).findById(1337);

    }

    @Test
    public void givenListOfCars_whenFindAll_thenAllCarsFound() {
        Car car1 = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        Car car2 = new Car(2, "BMW", "M5 F10", "some features", 2013, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        List<Car> expected = List.of(car1, car2);
        when(carRepository.findAll()).thenReturn(expected);
        List<Car> actual = carService.getAllCars();
        assertThat(actual).isNotNull();
        assertEquals(expected, actual);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void when_carRepository_findAvailableCars_then_return_carList() {
        Car car1 = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        Car car2 = new Car(2, "BMW", "M5 F10", "some features", 2013, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        List<Car> expected = List.of(car1, car2);
        when(carRepository.findAvailableCars()).thenReturn(expected);
        List<Car> actual = carService.getAvailableCars();
        assertThat(actual).isNotNull();
        assertEquals(expected, actual);
        verify(carRepository, times(1)).findAvailableCars();
    }
}