package com.minibackend.minibackend.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.minibackend.minibackend.Entity.Transaction;

@Component
public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
