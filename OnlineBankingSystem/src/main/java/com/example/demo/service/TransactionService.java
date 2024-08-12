package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AccountModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.repoJPA.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public TransactionModel createTransaction(TransactionModel transaction) {
		return transactionRepository.save(transaction);
	}
	
	public List<TransactionModel> getAllTransaction(){
		return transactionRepository.findAll();
	}
	
	public List<TransactionModel> getTransactionsByAccount(AccountModel account){
		return transactionRepository.findBySourceAccountOrDestinationAccount(account,account);		
	}

    public void deleteTransactionById(Long id) {
        Optional<TransactionModel> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction not found for this id :: " + id);
        }

        transactionRepository.deleteById(id);
    }
}
