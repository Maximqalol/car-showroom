package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.carshowroom.data.dto.CarDto;
import ru.example.carshowroom.data.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "producerId", source = "producer.id")
    CarDto toDto(Car car);
    @Mapping(target = "producer.id", source = "producerId")
    Car fromDto(CarDto carDto);

}
