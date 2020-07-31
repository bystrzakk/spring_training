package test.demo.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.demo.spring.core.converter.CustomerConverter;
import test.demo.spring.core.dto.CustomerDto;
import test.demo.spring.core.model.Customer;
import test.demo.spring.core.repository.CustomerRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        final Customer customer = CustomerConverter.convertToCustomer(customerDto);
        final Customer saved = customerRepository.save(customer);

        return CustomerConverter.convertToCustomerDto(saved);
    }

    public List<CustomerDto> getAllAuthors() {
        final List<Customer> allAuthorsFromDb = customerRepository.findAll();

        return allAuthorsFromDb.stream().map(CustomerConverter::convertToCustomerDto).collect(toList());
    }

    public boolean deleteCustomer(Long authorId) {
        customerRepository.deleteById(authorId);
        return true;
    }

    public CustomerDto findCustomerByFirstNameLastName(String firstName, String lastName) {
        final Customer customer = customerRepository.findByFirstNameAndLastName(firstName, lastName).get();
        return CustomerConverter.convertToCustomerDto(customer);
    }

}
