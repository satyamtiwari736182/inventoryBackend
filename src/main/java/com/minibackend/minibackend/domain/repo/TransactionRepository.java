package com.minibackend.minibackend.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.minibackend.minibackend.domain.entity.Transaction;

@Component
public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
