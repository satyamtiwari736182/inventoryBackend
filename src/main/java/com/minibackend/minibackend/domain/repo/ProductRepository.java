package com.minibackend.minibackend.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.minibackend.minibackend.domain.entity.Product;

@Component
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);

    void removeByName(String name);
}
