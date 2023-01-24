package ru.example.carshowroom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.carshowroom.data.dto.CustomerDto;
import ru.example.carshowroom.service.ICustomerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        path = "/api/customer",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Customer to create {}.", customerDto.toString());
        return customerService.create(customerDto);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        if (customerService.getCustomerById(customerId) == null) {
            log.error("Can't delete customer with id = {}", customerId);
        } else {
            customerService.remove(customerId);
            log.info("Customer with id = {} was deleted.", customerId);
        }
    }

    @GetMapping("/all")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerList = customerService.getCustomers();
        if (customerList.isEmpty()) {
            log.warn("List of customers is empty.");
        } else {
            log.info("Returned list of customers with size = {}.", customerList.size());
        }
        return customerList;
    }
}
