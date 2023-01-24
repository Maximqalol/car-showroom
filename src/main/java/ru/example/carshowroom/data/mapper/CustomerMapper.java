package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import ru.example.carshowroom.data.dto.CustomerDto;
import ru.example.carshowroom.data.entity.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer fromDto(CustomerDto dto);

    List<CustomerDto> toListDto(List<Customer> customers);
}
