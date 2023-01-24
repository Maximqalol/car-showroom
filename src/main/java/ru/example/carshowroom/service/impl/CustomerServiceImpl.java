package ru.example.carshowroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.example.carshowroom.data.dto.CustomerDto;
import ru.example.carshowroom.data.entity.Customer;
import ru.example.carshowroom.data.mapper.CustomerMapper;
import ru.example.carshowroom.data.repository.CustomerRepository;
import ru.example.carshowroom.service.ICustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        log.debug("Mapping CustomerDto to Customer.");
        Customer customer = customerMapper.fromDto(customerDto);
        log.debug("Saving and returning customer.");
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public void remove(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerMapper.toListDto(customerRepository.findAll(Sort.by("lastName")));
    }

    @Override
    public CustomerDto getCustomerById(Integer customerId) {
        return customerMapper.toDto(customerRepository.findById(customerId).orElse(null));
    }
}
