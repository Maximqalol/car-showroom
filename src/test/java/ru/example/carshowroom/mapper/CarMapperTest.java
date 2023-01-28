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
    public void shouldMapCarToCarDto() {
        Producer producer = new Producer(1, "Some name", "Some address", "Some phone");
        Car car = new Car(1, "BMW", "M5 F90", "some features", 2015, 7700000, 3, producer);
        CarDto carDto = carMapper.toDto(car);
        assertNotNull(carDto);
        assertEquals(carDto.getProducerId(), 1);
        assertEquals(carDto.getYear(), 2015);
        assertThat(carDto).hasFieldOrPropertyWithValue("brand", "BMW");
    }

    @Test
    public void shouldMapCarDtoToCar() {
        CarDto carDto = new CarDto(2, "BMW", "M5 F90", "some features", 2015, 7700000, 3, 1);
        Car car = carMapper.fromDto(carDto);
        assertNotNull(car);
        assertEquals(car.getProducer().getId(), 1);
        assertThat(car.getId()).isEqualTo(2);
        assertThat(car).hasFieldOrPropertyWithValue("model", "M5 F90");
    }

}