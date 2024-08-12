package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AccountModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService; 
	
	@PostMapping
	public TransactionModel createTransaction(@RequestBody TransactionModel transaction) {
		return transactionService.createTransaction(transaction);
	}
	
	@GetMapping
	public List<TransactionModel> getAllTransaction(){
		return transactionService.getAllTransaction();
	}
	
	@GetMapping("/account/{accountId}")
	public List<TransactionModel> getTransactionsByAccount(@PathVariable Long accountId){
		AccountModel account = new AccountModel();
		account.setAccountNumber(accountId);
		return transactionService.getTransactionsByAccount(account);
	}
	@DeleteMapping("/{id}")
	public void deleteTransactionById(@PathVariable Long id) {
		transactionService.deleteTransactionById(id);
	}
}
