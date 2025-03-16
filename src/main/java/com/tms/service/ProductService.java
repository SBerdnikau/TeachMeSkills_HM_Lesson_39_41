package com.tms.service;

import com.tms.model.Product;
import com.tms.model.dto.ProductRequestDto;
import com.tms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.getProductById(id);
    }

    public Optional<Product> createProduct(ProductRequestDto productRequestDto){
        Optional<Long> productId = productRepository.createProduct(productRequestDto);
        if(productId.isPresent()){
            return productRepository.getProductById(productId.get());
        }
        return Optional.empty();
    }

    public Optional<Product> updateProduct(ProductRequestDto productRequestDto){
        Boolean result = productRepository.updateProduct(productRequestDto);
        if(result){
            return getProductById(productRequestDto.getId());
        }
        return Optional.empty();
    }

    public Optional<Product> deleteProduct(Long id){
        Boolean result = productRepository.deleteProduct(id);
        if(result){
            return getProductById(id);
        }
        return Optional.empty();
    }

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }

}
