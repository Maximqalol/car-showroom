package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.data.entity.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(target = "car.id", source = "carId")
    @Mapping(target = "customer.id", source = "customerId")
    Request fromDto(RequestDto requestDto);

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "customerId", source = "customer.id")
    RequestDto toDto(Request request);

}
