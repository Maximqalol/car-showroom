package ru.example.carshowroom.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.mapper.CarMapper;
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

    @Mock
    private CarMapper carMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_carRepository_save_then_nothing() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, 1);
        when(carMapper.fromDto(carDto)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);
        carService.create(carDto);
        verify(carRepository).save(any(Car.class));
    }

    @Test
    public void when_carRepository_deleteById_then_nothing() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        carService.remove(car.getId());
        verify(carRepository).deleteById(anyInt());
    }

    @Test
    public void when_carRepository_update_then_nothing() {
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, 1);
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        when(carRepository.findById(carDto.getId())).thenReturn(Optional.of(car));
        when(carMapper.fromDto(carDto)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);
        carService.update(carDto);
        verify(carRepository).save(any());
    }

    @Test
    public void when_carRepository_findById_then_return_carDto() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, 1);
        when(carMapper.toDto(car)).thenReturn(carDto);
        when(carRepository.findById(1337)).thenReturn(Optional.of(car));
        CarDto carDto1 = carService.findCarById(1337);
        CarDto carDto2 = carService.findCarById(1338);
        assertEquals(carDto1.getId(), car.getId());
        assertThat(carDto2).isNull();
        verify(carRepository, atLeastOnce()).findById(1337);

    }

    @Test
    public void when_carRepository_findAll_then_return_carList() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        when(carRepository.findAll()).thenReturn(List.of(car, car, car));
        List<CarDto> carList = carService.getAllCars();
        assertThat(carList).isNotNull();
        assertEquals(3, carList.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void when_carRepository_findAvailableCars_then_return_carList() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 1, new Producer(1, "Some name", "Some address", "Some phone"));
        when(carRepository.findAvailableCars()).thenReturn(List.of(car, car, car));
        List<CarDto> carList = carService.getAvailableCars();
        assertThat(carList).isNotNull();
        assertEquals(3, carList.size());
        verify(carRepository, times(1)).findAvailableCars();
    }
}