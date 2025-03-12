package com.teachmeskills.market_32.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private Long id;
    private String nameProduct;
    private Double price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
