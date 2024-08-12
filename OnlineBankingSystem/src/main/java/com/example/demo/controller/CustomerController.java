package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CustomerModel;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public CustomerModel createCustomer(@RequestBody CustomerModel customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id){
		return customerService.getCustomerById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public List<CustomerModel> getAllCustomer(){
		return customerService.getAllCustomer();
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id,@RequestBody CustomerModel customerDetails){
		return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }
}
