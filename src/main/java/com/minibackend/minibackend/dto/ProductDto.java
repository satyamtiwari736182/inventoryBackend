package com.minibackend.minibackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @NotEmpty(message = "Name is required")
    @Size(min = 3)
    private String name;

    @NotNull(message = "Quantity with positive value is required")
    @Min(value = 1)
    private int quantity;

    @NotNull(message = "Price with positive value is required")
    @Min(value = 1)
    private double price;
}
