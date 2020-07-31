package test.demo.spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.spring.core.dto.CustomerDto;
import test.demo.spring.core.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDto) {
        final CustomerDto newCustomer = customerService.addNewCustomer(customerDto);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(name = "firstName", required = false) String firstName,
                                                          @RequestParam(name = "lastName", required = false) String lastName) {
        final List<CustomerDto> allCustomers = new ArrayList<>();
        if (isEmpty(firstName) && isEmpty(lastName)) {
            allCustomers.addAll(customerService.getAllAuthors());
        } else {
            allCustomers.add(customerService.findCustomerByFirstNameLastName(firstName, lastName));
        }

        return new ResponseEntity<>(allCustomers, OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), ACCEPTED);
    }

}
