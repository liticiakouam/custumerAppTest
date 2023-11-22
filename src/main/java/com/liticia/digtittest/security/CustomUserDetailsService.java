package com.liticia.digtittest.security;

import com.liticia.digtittest.entity.Customer;
import com.liticia.digtittest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (!Objects.isNull(customer))
            return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("user not found");
    }
}
