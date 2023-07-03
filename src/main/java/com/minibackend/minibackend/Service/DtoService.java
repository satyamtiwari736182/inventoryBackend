package com.minibackend.minibackend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minibackend.minibackend.DTO.ProductDto;
import com.minibackend.minibackend.Entity.Product;

@Service
public class DtoService {
    @Autowired
    private ModelMapper modelMapper;

    public Product dtoToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
