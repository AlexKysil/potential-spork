package com.example.potential_spork.services;

import dal.models.Customer;
import dal.repository.iShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {

        private final iShopRepository repository;

        public List<Customer> searchCustomers(String firstName, String lastName, String email, String postcode) {
            return repository.searchCustomers(firstName, lastName, email, postcode);
        }

        public Optional<Customer> getCustomerById(UUID id) {
            return repository.readCustomer(id);
        }

        public Customer createCustomer(Customer customer) {
            return repository.saveCustomer(customer);
        }

        public Customer saveCustomer(Customer customer) {
            return repository.saveCustomer(customer);
        }

        public void deleteCustomer(UUID id) {
            repository.deleteCustomer(id);
        }
    }