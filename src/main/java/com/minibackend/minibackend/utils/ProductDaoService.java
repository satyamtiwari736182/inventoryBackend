package com.minibackend.minibackend.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.DAO.ProductRepository;
import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.Error.Exception.ProductNotFoundException;

@Service
public class ProductDaoService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        Product inventoryProduct = productRepository.findByName(product.getName());
        if (inventoryProduct != null)
            return product;
        productRepository.save(product);
        return product;
    }

    public Product getProduct(String name) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("No such product [" + name + "] is available");
        return product;
    }

    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    public Product removeProduct(String name) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("invalid product");
        productRepository.removeByName(name);
        return product;
    }

    public Product updateProductPrice(String name, Double price) {
        Product product = productRepository.findByName(name);
        if (product == null && price <= 0)
            throw new ProductNotFoundException("invalid product");
        product.setPrice(price);
        productRepository.save(product);
        return product;
    }

    public List<Product> updateProducts(List<Product> products, boolean isSale) {
        List<Product> productList = products.stream().map(product -> {
            Product inventoryProduct = productRepository.findByName(product.getName());
            if (isSale)
                inventoryProduct.setQuantity(inventoryProduct.getQuantity() - product.getQuantity());
            else
                inventoryProduct.setQuantity(inventoryProduct.getQuantity() + product.getQuantity());
            inventoryProduct.setPrice(product.getPrice());
            productRepository.save(inventoryProduct);

            product.setId(inventoryProduct.getId());
            product.setCategory(inventoryProduct.getCategory());
            return product;
        }).toList();

        return productList;
    }

}
