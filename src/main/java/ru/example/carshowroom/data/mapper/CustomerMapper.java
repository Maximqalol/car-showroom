package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import ru.example.carshowroom.data.dto.CustomerDto;
import ru.example.carshowroom.data.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer fromDto(CustomerDto customerDto);

}
