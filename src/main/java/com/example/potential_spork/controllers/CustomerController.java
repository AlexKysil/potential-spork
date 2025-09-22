package com.example.potential_spork.controllers;

import com.example.potential_spork.helpers.PatchHelper;
import com.example.potential_spork.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import dal.models.Customer;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

        private final CustomerService service;
        private final PatchHelper patchHelper;


    @GetMapping("/search")

    public ResponseEntity<List<Customer>> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String postcode) {
            // TODO: Add Parametrized search in future
        List<Customer> results = service.searchCustomers(firstName, lastName, email, postcode);
        return (ResponseEntity<List<Customer>>) ResponseEntity.ok(results);

    }

        @GetMapping("/{id}")
        public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
            return service.getCustomerById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
            return new ResponseEntity<>(service.createCustomer(customer), HttpStatus.CREATED);
        }

        @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
        public ResponseEntity<Customer> patchCustomer(@PathVariable UUID id, @RequestBody JsonPatch patch) {
        try {
            Customer existingCustomer = service.getCustomerById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

            Customer patchedCustomer = patchHelper.applyPatch(patch, existingCustomer, Customer.class);
            Customer updatedCustomer = service.saveCustomer(patchedCustomer);

            return ResponseEntity.ok(updatedCustomer);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
            service.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        }
}
