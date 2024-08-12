package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AccountModel;
import com.example.demo.model.CustomerModel;
import com.example.demo.repoJPA.AccountRepository;
import com.example.demo.repoJPA.CustomerRepository;

@Service
public class AccountService {
	
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public AccountModel createAccount(AccountModel account) {
			CustomerModel customer = customerRepository.findById(account.getCustomer().getId())
			    .orElse(null);

			if (customer == null) {
			    throw new ResourceNotFoundException("Customer not found for this id :: " + account.getCustomer().getId());
			}
	        account.setCustomer(customer);
		return accountRepository.save(account);
	}
	
	public Optional<AccountModel> getAccountById(Long accountNumber){
		
		return accountRepository.findById(accountNumber);
	}
	
	public List<AccountModel> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	public AccountModel updateAccount(Long accountNumber, AccountModel accountDetails) {
		Optional<AccountModel> optionalAccount = accountRepository.findById(accountNumber);
		
		if(optionalAccount.isPresent()) {
			AccountModel account = optionalAccount.get();
			account.setAccountType(accountDetails.getAccountType());
			account.setBalance(accountDetails.getBalance());
			
			if(accountDetails.getCustomer()!=null) {
				CustomerModel customer = customerRepository.findById(accountDetails.getCustomer().getId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + accountDetails.getCustomer().getId()));
	                account.setCustomer(customer);
			}else {
				System.out.println("Customer details are missing in the update request.");
			}
			return accountRepository.save(account);
		}else {
			return null;
		}
		
		
	}
	
	public void deleteAccount(Long accountNumber) {
		accountRepository.deleteById(accountNumber);
	}
	

}
