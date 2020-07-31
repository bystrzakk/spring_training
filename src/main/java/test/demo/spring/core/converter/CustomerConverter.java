package test.demo.spring.core.converter;

import test.demo.spring.core.dto.CustomerDto;
import test.demo.spring.core.model.Customer;

public class CustomerConverter {

    private CustomerConverter() {
    }

    public static Customer convertToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .build();
    }

    public static CustomerDto convertToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .id(customer.getId())
                .email(customer.getEmail())
                .build();
    }
}
