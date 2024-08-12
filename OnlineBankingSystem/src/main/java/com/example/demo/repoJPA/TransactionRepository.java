package com.example.demo.repoJPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AccountModel;
import com.example.demo.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
	
	 List<TransactionModel> findBySourceAccountOrDestinationAccount(AccountModel sourceAccount, AccountModel destinationAccount);
}
