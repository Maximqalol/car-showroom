package ru.example.carshowroom.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.mapper.CarMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarMapperTest {

    @Mock
    private CarMapper carMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mapCarToDto() {
        Car car = new Car();
        CarDto carDto = new CarDto();
        carDto.setBrand("BMW");
        when(carMapper.toDto(car)).thenReturn(carDto);
        CarDto mapperCar = carMapper.toDto(car);
        assertThat(mapperCar).isNotNull();
        assertThat(mapperCar).hasFieldOrPropertyWithValue("brand", "BMW");
        verify(carMapper, times(1)).toDto(car);
    }

    @Test
    public void mapCarFromDto() {
        CarDto carDto = new CarDto();
        Car car = new Car();
        car.setId(1337);
        when(carMapper.fromDto(carDto)).thenReturn(car);
        Car mapperCar = carMapper.fromDto(carDto);
        assertThat(mapperCar).isNotNull();
        assertEquals(1337, mapperCar.getId());
        verify(carMapper, atLeastOnce()).fromDto(carDto);
    }

}