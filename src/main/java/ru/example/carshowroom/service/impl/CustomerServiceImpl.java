package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.entity.Customer;
import ru.example.carshowroom.data.repository.CustomerRepository;
import ru.example.carshowroom.service.ICustomerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("Save customer.");
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Integer customerId) {
        log.debug("Delete customer with id = {}.", customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        log.debug("Find customer by id = {}.", customerId);
        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Can't find customer with id = " + customerId));
    }

    @Override
    public List<Customer> getCustomers() {
        log.debug("Find all customers.");
        return customerRepository.findAll(Sort.by("lastName"));
    }
}
