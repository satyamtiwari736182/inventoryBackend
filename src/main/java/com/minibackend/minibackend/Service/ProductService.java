package com.minibackend.minibackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.utils.ProductDaoService;
import com.minibackend.minibackend.utils.ProductUtilityService;

@Service
public class ProductService {
    @Autowired
    private ProductDaoService productDaoService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    ProductUtilityService productUtility;

    public Product add(Product product) {
        return productDaoService.addProduct(product);
    }

    public Product get(String name) {
        return productDaoService.getProduct(name);
    }

    public List<Product> get() {
        return productDaoService.getAllProduct();
    }

    public Product remove(String name) {
        return productDaoService.removeProduct(name);
    }

    public Product updatePrice(String name, Double price) {
        return productDaoService.updateProductPrice(name, price);
    }

    public List<Product> purchase(List<Product> products) {
        products = productDaoService.updateProducts(products, false);
        transactionService.add(products, false);
        return products;
    }

    public List<Product> sale(List<Product> products) {
        products = productDaoService.updateProducts(products, true);
        transactionService.add(products, true);
        return products;
    }
}
