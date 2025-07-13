package com.example.productcatalogapiservice.services;

import com.example.productcatalogapiservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Product input,Long id);

    List<Product> getAllProducts();
}
