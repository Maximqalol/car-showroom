package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Producer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "producer", source = "producerId", qualifiedByName = "setProducerId")
    Car fromDto(CarDto carDto);
    @Mapping(target = "producerId", source = "producer.id")
    CarDto toDto(Car car);

    List<CarDto> toListDto(List<Car> cars);

    @Named("setProducerId")
    default Producer setProducerId(Integer producerId) {
        if (producerId == null) {
            return null;
        }
        final var producer = new Producer();
        producer.setId(producerId);
        return producer;
    }
}
