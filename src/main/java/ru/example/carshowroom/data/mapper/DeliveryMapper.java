package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.carshowroom.data.dto.DeliveryDto;
import ru.example.carshowroom.data.entity.Delivery;
import ru.example.carshowroom.data.entity.Request;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(target = "request", source = "requestId", qualifiedByName = "setRequestId")
    Delivery fromDto(DeliveryDto deliveryDto);

    @Mapping(target = "requestId", source = "request.id")
    DeliveryDto toDto(Delivery delivery);

    List<DeliveryDto> toListDto(List<Delivery> deliveries);

    @Named("setRequestId")
    default Request setRequestId(Integer requestId) {
        if (requestId == null) {
            return null;
        }
        final var request = new Request();
        request.setId(requestId);
        return request;
    }
}
