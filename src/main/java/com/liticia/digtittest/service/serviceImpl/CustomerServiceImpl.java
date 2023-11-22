package com.liticia.digtittest.service.serviceImpl;

import com.liticia.digtittest.entity.Customer;
import com.liticia.digtittest.exception.UserAlreadyExistException;
import com.liticia.digtittest.repository.CustomerRepository;
import com.liticia.digtittest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) throws UserAlreadyExistException {
        Customer customerByUsername = customerRepository.findByUsername(customer.getUsername());
        if (customerByUsername != null) {
            throw new UserAlreadyExistException();
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        updatedCustomer.setBirthDate(customer.getBirthDate());
        updatedCustomer.setFirstname(customer.getFirstname());
        updatedCustomer.setLastname(customer.getLastname());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomer.setUsername(customer.getUsername());

        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id).get();
    }
}
