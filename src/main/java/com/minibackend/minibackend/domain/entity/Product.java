package com.minibackend.minibackend.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.minibackend.minibackend.domain.enums.ProductTypes;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Name is required")
    @Size(min = 3)
    private String name;
    @Builder.Default
    private int quantity = 0;
    @Builder.Default
    private double price = 0;
    @Builder.Default
    private ProductTypes category = ProductTypes.OTHERS;
}
