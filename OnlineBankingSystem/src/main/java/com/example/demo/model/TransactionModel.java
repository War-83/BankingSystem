package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	
	@ManyToOne
	@JoinColumn(name="source_account", nullable=false)
	private AccountModel sourceAccount;
	
	@ManyToOne
	@JoinColumn(name="destination_account", nullable=false)
	private AccountModel destinationAccount;

	
    public TransactionModel() {
    }
	public TransactionModel(Long transactionId, BigDecimal amount, LocalDateTime transactionDate,
			AccountModel sourceAccount, AccountModel destinationAccount) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
	}
    // Getters and Setters

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountModel getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountModel sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountModel getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountModel destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Override
    public String toString() {
        return "TransactionModel [transactionId=" + transactionId + ", amount=" + amount + ", transactionDate="
                + transactionDate + ", sourceAccount=" + sourceAccount + ", destinationAccount="
                + destinationAccount + "]";
    }
	
	
	
}
