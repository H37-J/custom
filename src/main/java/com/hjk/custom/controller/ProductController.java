package com.hjk.custom.controller;

import com.hjk.custom.entity.Product;
import com.hjk.custom.entity.User;
import com.hjk.custom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @MutationMapping
    public Product saveProduct(@Argument String name, @Argument User user) {
        return productService.save(name, user);
    }

    @QueryMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
