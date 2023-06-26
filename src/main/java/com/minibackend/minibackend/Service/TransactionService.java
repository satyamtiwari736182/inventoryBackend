package com.minibackend.minibackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.DAO.TransactionRepository;
import com.minibackend.minibackend.Entity.Transaction;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> get() {
        return transactionRepository.findAll();
    }

    public Transaction add(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
