package ru.example.carshowroom.service;


import ru.example.carshowroom.data.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    CustomerDto create(CustomerDto customerDto);

    void remove(Integer customerId);

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(Integer customerId);
}
