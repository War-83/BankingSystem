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

import com.example.demo.model.AccountModel;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public AccountModel createAccount(@RequestBody AccountModel account) {
		return accountService.createAccount(account);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountModel> getAccountById(@PathVariable Long id){
		return accountService.getAccountById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public List<AccountModel> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountModel> updateAccount(@PathVariable Long id,@RequestBody AccountModel accountDetails){
		AccountModel updatedAccount = accountService.updateAccount(id, accountDetails);
		if(updatedAccount != null) {
			return ResponseEntity.ok(updatedAccount);
		}else {
			 return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}
	
}
