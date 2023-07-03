package com.minibackend.minibackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.Entity.Transaction;
import com.minibackend.minibackend.utils.OrderTypes;
import com.minibackend.minibackend.utils.TransactionDaoService;

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
