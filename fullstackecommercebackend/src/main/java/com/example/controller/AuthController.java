package com.example.controller;


import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/signup")
    public Customer register(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PostMapping("/login")
    public Customer login(@RequestBody Customer loginData) {
        Customer customer = customerRepository.findByEmail(loginData.getEmail());
        if (customer != null && customer.getPassword().equals(loginData.getPassword())) {
            return customer;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
