package com.example.productcatalogapiservice.controllers;

import com.example.productcatalogapiservice.dtos.ProductDto;
import com.example.productcatalogapiservice.models.Product;
import com.example.productcatalogapiservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception {
        //Arrange

        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Product 1");
        productDtos.add(productDto);

        Product product1 = new Product();
        List<Product> products = new ArrayList<>();
        product1.setId(1L);
        product1.setName("Product 1");
        products.add(product1);
        when(productService.getAllProducts()).thenReturn(products);


        String response = objectMapper.writeValueAsString(productDtos);
        System.out.println(response);

        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(response));


    }




}
