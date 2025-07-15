package com.example.productcatalogapiservice.controllers;

import com.example.productcatalogapiservice.dtos.ProductDto;
import com.example.productcatalogapiservice.models.Product;
import com.example.productcatalogapiservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void testGetProductById_WithValidId_RunsSuccessfully() {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone17");
        when(productService.getProductById(id)).thenReturn(product);
        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(id);


        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(productDtoResponseEntity.getBody().getName(), product.getName());
        assertEquals(productDtoResponseEntity.getBody().getId(), product.getId());
        verify(productService,times(1)).getProductById(id);
    }

    @Test
    public void testGetProductById_WithInvalidId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> productController.getProductById(-1L));
        verify(productService,times(0)).getProductById(-1L);
    }

    @Test
    public void testGetProductById_WithZeroId_ThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->productController.getProductById(0L));
        assertEquals("Products exist with positive id", exception.getMessage());

    }

}