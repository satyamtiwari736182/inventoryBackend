package com.minibackend.minibackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.DAO.ProductRepository;
import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.Error.Exception.ProductNotFoundException;
import com.minibackend.minibackend.Error.Exception.ProductOutOfStockException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product get(String name) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("No such product [" + name + "] is available");

        return product;
    }

    public List<Product> get() {
        return (List<Product>) productRepository.findAll();
    }

    public Product remove(String name) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("invalid product");
        productRepository.removeByName(name);
        return product;
    }

    public Product updatePrice(String name, Double price) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("invalid product");
        product.setPrice(price);
        productRepository.save(product);
        return product;
    }

    public Product purchase(Product purchasedProduct) {
        Product product = productRepository.findByName(purchasedProduct.getName());
        if (product == null)
            throw new ProductNotFoundException("invalid product");

        product.setQuantity(product.getQuantity() + purchasedProduct.getQuantity());
        product.setPrice(purchasedProduct.getPrice());
        productRepository.save(product);

        purchasedProduct.setId(product.getId());
        purchasedProduct.setCategory(product.getCategory());

        return purchasedProduct;
    }

    public List<Product> sale(List<Product> products) {

        for (Product soldProduct : products) {
            Product productStock = productRepository.findByName(soldProduct.getName());
            if (productStock == null)
                throw new ProductNotFoundException("invalid product");
            if (productStock.getQuantity() < soldProduct.getQuantity())
                throw new ProductOutOfStockException(productStock.getName() + ", out of stock");
        }

        for (Product soldProduct : products) {
            Product productStock = productRepository.findByName(soldProduct.getName());
            productStock.setQuantity(productStock.getQuantity() - soldProduct.getQuantity());
            productRepository.save(productStock);
            
            soldProduct.setId(productStock.getId());
            soldProduct.setCategory(productStock.getCategory());
        }

        return products;
    }

    public Product add(Product product) {
        Product inventoryProduct = productRepository.findByName(product.getName());
        if (inventoryProduct != null)
            return product;
        productRepository.save(product);
        return product;
    }
}
