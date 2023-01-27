package ru.example.carshowroom.service;

import ru.example.carshowroom.data.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    void create(CustomerDto customerDto);

    void remove(Integer customerId);

    void update(CustomerDto customerDto);

    CustomerDto getCustomerById(Integer customerId);

    List<CustomerDto> getCustomers();

}
