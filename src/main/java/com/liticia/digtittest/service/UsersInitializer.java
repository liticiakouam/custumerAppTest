package com.liticia.digtittest.service;

import com.liticia.digtittest.entity.Customer;
import com.liticia.digtittest.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersInitializer {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersInitializer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (customerRepository.count() != 0) {
            return;
        }

        Customer customer = new Customer();
        customer.setFirstname("Admin");
        customer.setLastname("Admin");
        customer.setUsername("admin");
        customer.setPassword(passwordEncoder.encode("admin"));

        customerRepository.save(customer);
        log.info("Default user initialized successfully.");
    }
}