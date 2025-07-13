package com.example.productcatalogapiservice.repos;

import com.example.productcatalogapiservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findProductByPriceBetween(Double price1,Double price2);

    List<Product> findAllByOrderByPrice();

    @Query("SELECT p.name FROM Product p where p.id=:id")
    String getMeNameOfMyFavouriteProductWhoseIdIWillGiveYou(Long id);

}
