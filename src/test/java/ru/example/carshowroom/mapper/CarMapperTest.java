package ru.example.carshowroom.mapper;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;
import ru.example.carshowroom.data.mapper.CarMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CarMapperTest {

    @Spy
    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);


    @Test
    public void when_map_car_toDto_then_return_carDto() {
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, new Producer(1, "Some name", "Some address", "Some phone"));
        CarDto mappedCarDto = carMapper.toDto(car);
        assertThat(mappedCarDto).isNotNull();
        assertEquals(car.getProducer().getId(), mappedCarDto.getProducerId());
        assertThat(mappedCarDto).hasFieldOrPropertyWithValue("brand", "BMW");
    }

    @Test
    public void when_map_car_fromDto_then_return_car() {
        CarDto carDto = new CarDto();
        Car mappedCar = carMapper.fromDto(carDto);
        assertThat(mappedCar).isNotNull();
        assertEquals(carDto.getProducerId(), mappedCar.getProducer().getId());
        assertThat(carDto.getId()).isEqualTo(mappedCar.getId());
    }

}