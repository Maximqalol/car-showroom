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
    public void createCarTest() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        when(carRepository.save(car)).thenReturn(car);
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, 2);
        when(carMapper.fromDto(carDto)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);
        //assertEquals(car2, carMapper.toDto(car));
        CarDto savedCar = carService.create(carDto);
        assertThat(savedCar).isNotNull();
    }

    @Test
    public void findCarTest() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        CarDto carDto = new CarDto(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, 1);
        when(carMapper.toDto(car)).thenReturn(carDto);
        when(carRepository.findCarById(1337)).thenReturn(car);
        CarDto carDto1 = carService.findCarById(1337);
        CarDto carDto2 = carService.findCarById(1338);
        assertEquals(carDto1.getId(), car.getId());
        assertThat(carDto2).isNull();
        verify(carRepository, atLeastOnce()).findCarById(1337);

    }

    @Test
    public void getAllCarsTest() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 5700000, 3, new Producer());
        when(carRepository.findAll()).thenReturn(List.of(car, car, car));
        when(carMapper.toListDto(List.of(car, car, car))).thenReturn(List.of(new CarDto(), new CarDto(), new CarDto()));
        List<CarDto> carList = carService.getAllCars();
        assertEquals(3, carList.size());
        assertThat(carList).isNotNull();
        verify(carRepository, times(1)).findAll();
    }
}