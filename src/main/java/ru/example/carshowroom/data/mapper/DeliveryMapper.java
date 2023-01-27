package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.carshowroom.data.dto.DeliveryDto;
import ru.example.carshowroom.data.entity.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(target = "requestId", source = "request.id")
    DeliveryDto toDto(Delivery delivery);

    @Mapping(target = "request.id", source = "requestId")
    Delivery fromDto(DeliveryDto deliveryDto);

}
