package com.example.bankaccounts.service.impl;

import com.example.bankaccounts.dto.CustomerDTO;
import com.example.bankaccounts.exception.CustomerIDDuplicatedException;
import com.example.bankaccounts.exception.CustomerNotFoundException;
import com.example.bankaccounts.model.Customer;
import com.example.bankaccounts.repository.CustomerRepository;
import com.example.bankaccounts.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        customerRepository.findById(customerDTO.getId())
                .ifPresent(c->{
                    throw new CustomerIDDuplicatedException(customerDTO.getId());
                });
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customerRepository.save(customer);
        return this.modelMapper.map(customer,CustomerDTO.class);
    }



}
