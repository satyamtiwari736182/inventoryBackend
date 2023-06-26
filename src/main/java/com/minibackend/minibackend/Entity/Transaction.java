package com.minibackend.minibackend.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.minibackend.minibackend.utils.OrderTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Transaction {
    @Id
    String id;
    private List<Product> products;
    private OrderTypes category;
}
