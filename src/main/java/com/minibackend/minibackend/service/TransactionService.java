package com.minibackend.minibackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.domain.TransactionDaoService;
import com.minibackend.minibackend.domain.entity.Product;
import com.minibackend.minibackend.domain.entity.Transaction;
import com.minibackend.minibackend.domain.enums.OrderTypes;

@Service
public class TransactionService {
    @Autowired
    private TransactionDaoService transactionDaoService;

    public List<Transaction> get() {
        return transactionDaoService.getAllTransactions();
    }

    public Transaction add(List<Product> products, boolean isSale) {
        Transaction transaction = Transaction.builder().products(products)
                .category(isSale ? OrderTypes.SALE : OrderTypes.PURCHASE)
                .build();
        return transactionDaoService.addTransaction(transaction);
    }

}
