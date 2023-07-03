package com.minibackend.minibackend.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.DAO.ProductRepository;
import com.minibackend.minibackend.DTO.ProductDto;
import com.minibackend.minibackend.Entity.Product;
import com.minibackend.minibackend.Error.Exception.ProductNotFoundException;
import com.minibackend.minibackend.Error.Exception.ProductOutOfStockException;

@Service
public class ProductUtilityService {
    @Autowired
    DtoService dtoService;

    @Autowired
    ProductRepository productRepository;

    public List<Product> validateProduct(List<ProductDto> productDtos, boolean isSale) {
        List<Product> products = productDtos.stream().map(productDto -> {
            Product product = dtoService.dtoToProduct(productDto);
            Product inventoryProduct = productRepository.findByName(product.getName());
            
            if (inventoryProduct == null)
                throw new ProductNotFoundException("invalid product");

            if (isSale && inventoryProduct.getQuantity() < product.getQuantity())
                throw new ProductOutOfStockException(product.getName() + ", out of stock");

            return product;
        }).toList();
        return products;
    }
}
