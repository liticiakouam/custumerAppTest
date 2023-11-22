package com.liticia.digtittest.service;

import com.liticia.digtittest.entity.Customer;
import com.liticia.digtittest.exception.UserAlreadyExistException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer save(Customer customer) throws UserAlreadyExistException;
    Customer update(Customer customer);
    void delete(int id);
    Customer getById(int id);
}
