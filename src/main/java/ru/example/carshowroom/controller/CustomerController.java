package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.CustomerDto;
import ru.example.carshowroom.data.mapper.CustomerMapper;
import ru.example.carshowroom.service.ICustomerService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/customer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerMapper customerMapper;

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(CustomerMapper customerMapper, ICustomerService customerService) {
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Customer to create: {}.", customerDto.toString());
        customerService.create(customerMapper.fromDto(customerDto));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        log.info("Remove customer with id = {}.", customerId);
        customerService.remove(customerId);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Customer to update: {}.", customerDto.toString());
        customerService.update(customerMapper.fromDto(customerDto));
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Integer customerId) {
        log.info("Return customer with id = {}.", customerId);
        return customerMapper.toDto(customerService.getCustomerById(customerId));
    }

    @GetMapping("/all")
    public List<CustomerDto> getAllCustomers() {
        log.info("Return list of customers.");
        return customerService.getCustomers().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }
}
