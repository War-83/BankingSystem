package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CustomerModel;
import com.example.demo.repoJPA.CustomerRepository;

@Service
public class CustomerService {
	
		@Autowired
		private CustomerRepository customerRepository;
		
		public CustomerModel createCustomer(CustomerModel customer) {
			return customerRepository.save(customer);
		}
		
		public Optional<CustomerModel> getCustomerById(Long id){
			return customerRepository.findById(id);
		}
		
		public List<CustomerModel> getAllCustomer(){
			return customerRepository.findAll();
		}
		
		public CustomerModel updateCustomer(Long id,CustomerModel customerDetails) {
			Optional<CustomerModel> optionalCustomer = customerRepository.findById(id);
			
			if(optionalCustomer.isPresent()) {
				CustomerModel customer = optionalCustomer.get();
				customer.setName(customerDetails.getName());
				customer.setEmail(customerDetails.getEmail());
				customer.setPassword(customerDetails.getPassword());
				return customerRepository.save(customer);
			}else {
				return null;
			}
		}
		
		public void deleteCustomerById(Long id) {
		    Optional<CustomerModel> optionalCustomer = customerRepository.findById(id);

		    if (optionalCustomer.isEmpty()) {
		        throw new ResourceNotFoundException("Customer not found for this id :: " + id);
		    }

		    customerRepository.delete(optionalCustomer.get());
		}
}
