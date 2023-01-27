package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer create(Customer customer);

    void remove(Integer customerId);

    Customer update(Customer customer);

    Customer getCustomerById(Integer customerId);

    List<Customer> getCustomers();

}
