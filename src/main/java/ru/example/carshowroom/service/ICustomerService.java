package ru.example.carshowroom.service;

import ru.example.carshowroom.data.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    void remove(Integer customerId);

    Customer getCustomerById(Integer customerId);

    List<Customer> getCustomers();

}
