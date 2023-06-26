package com.minibackend.minibackend.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.minibackend.minibackend.utils.ProductTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document
public class Product {
    @Id
    private String id;
    private String name;
    @Builder.Default
    private int quantity = 0;
    @Builder.Default
    private double price = 0;
    @Builder.Default
    private ProductTypes category = ProductTypes.OTHERS;
}
