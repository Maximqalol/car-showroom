package ru.example.carshowroom.repository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class CarRepositoryTest {

    @MockBean
    private CarRepository carRepository;

    @Test
    public void whenFindCarById_thenReturnCarTest() {
        int carId = 1;
        Car car = new Car(carId, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        when(carRepository.findCarById(carId)).thenReturn(car);
        Car foundedCar = carRepository.findCarById(carId);
        assertThat(foundedCar).isNotNull();
        assertEquals(car.getBrand(), foundedCar.getBrand());
        Mockito.verify(carRepository, Mockito.times(1)).findCarById(Mockito.any());
    }

    @Test
    public void whenFindAll_thenReturnAllCarsTest() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        Car car2 = new Car(2, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        when(carRepository.findAll()).thenReturn(List.of(car, car2));
        List<Car> cars = carRepository.findAll();
        assertThat(cars.size()).isEqualTo(2);

    }
}