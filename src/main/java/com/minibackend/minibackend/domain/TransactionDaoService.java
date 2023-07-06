package com.minibackend.minibackend.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.domain.entity.Transaction;
import com.minibackend.minibackend.domain.repo.TransactionRepository;

@Service
public class TransactionDaoService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
