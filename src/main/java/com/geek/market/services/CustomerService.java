package com.geek.market.services;


import com.geek.market.entities.Customer;
import com.geek.market.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByName(String name){
        return customerRepository.findByName(name);
    }

}
