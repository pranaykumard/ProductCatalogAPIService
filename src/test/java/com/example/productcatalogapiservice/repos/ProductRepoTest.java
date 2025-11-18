package com.example.productcatalogapiservice.repos;

import com.example.productcatalogapiservice.models.Category;
import com.example.productcatalogapiservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;


    @Test
    public void addProductsIntoRds() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Boomer");
        product1.setPrice(10D);
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Bubble Gum");
        product1.setCategory(category1);
        productRepo.save(product1);


        Product product2 = new Product();
        product2.setId(10L);
        product2.setName("IMac");
        product2.setPrice(200000D);
        Category category2 = new Category();
        category2.setId(100L);
        category2.setName("Electronics");
        product2.setCategory(category2);
        productRepo.save(product2);
    }


    //@Test
    public void testQueries() {
//        List<Product> productList = productRepo.findAllByOrderByPrice();
//        for(Product product : productList) {
//            System.out.println(product.getPrice());
//        }

        System.out.println(productRepo.getMeNameOfMyFavouriteProductWhoseIdIWillGiveYou(2L));
    }
}