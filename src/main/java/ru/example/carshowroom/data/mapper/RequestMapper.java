package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.example.carshowroom.data.dto.RequestDto;
import ru.example.carshowroom.data.entity.Car;
import ru.example.carshowroom.data.entity.Customer;
import ru.example.carshowroom.data.entity.Request;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(target = "car", source = "carId", qualifiedByName = "setCarId")
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "setCustomerId")
    Request fromDto(RequestDto requestDto);

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "customerId", source = "customer.id")
    RequestDto toDto(Request request);

    List<RequestDto> toListDto(List<Request> requests);

    @Named("setCarId")
    default Car setCarId(Integer carId) {
        if (carId == null) {
            return null;
        }
        final var car = new Car();
        car.setId(carId);
        return car;
    }

    @Named("setCustomerId")
    default Customer setCustomerId(Integer customerId) {
        if (customerId == null) {
            return null;
        }
        final var customer = new Customer();
        customer.setId(customerId);
        return customer;
    }
}
