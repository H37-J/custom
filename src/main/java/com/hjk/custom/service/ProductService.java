package com.hjk.custom.service;

import com.hjk.custom.entity.Product;
import com.hjk.custom.entity.User;
import com.hjk.custom.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(String name, User user) {
        var data = Product.builder().name(name).user(user).build();
        return productRepository.save(data);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
