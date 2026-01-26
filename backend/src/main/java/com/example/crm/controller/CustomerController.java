package com.example.crm.controller;

import com.example.crm.model.Customer;
import com.example.crm.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

 
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @RequestBody Customer updatedCustomer) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setCompany(updatedCustomer.getCompany());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setAssignedSalesRep(updatedCustomer.getAssignedSalesRep());
        customer.setNotes(updatedCustomer.getNotes());

        return customerRepository.save(customer);
    }

    
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
}