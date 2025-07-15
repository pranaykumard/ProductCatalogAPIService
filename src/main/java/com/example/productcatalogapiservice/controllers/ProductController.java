package com.example.productcatalogapiservice.controllers;

import com.example.productcatalogapiservice.dtos.CategoryDto;
import com.example.productcatalogapiservice.dtos.ProductDto;
import com.example.productcatalogapiservice.models.Category;
import com.example.productcatalogapiservice.models.Product;
import com.example.productcatalogapiservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    //@Qualifier("fakeStoreProductService")
    IProductService productService;


    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        List<Product> products =  productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = from(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        // try {
        if (productId < 0) {
            throw new IllegalArgumentException("Product Id not found");
        }
        else if(productId == 0) {
            throw new IllegalArgumentException("Products exist with positive id");
        }
        Product product = productService.getProductById(productId);
        if (product == null) return null;

        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
//        }catch (IllegalArgumentException exception){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping("products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.createProduct(product);
        if(output == null) return null;
        return  from(output);
    }

    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.replaceProduct(product,id);
        if(output == null) return null;
        return  from(output);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }


}
