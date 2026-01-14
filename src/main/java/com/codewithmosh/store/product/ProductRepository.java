package com.codewithmosh.store.product;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findByCategory_Id(Byte categoryId);

    //if the query not follow naming convention in JPA, need add @Query
    @EntityGraph(attributePaths = "category")//this is one to many setter name
    @Query("SELECT p from Product p")
    List<Product> findAllWithCategory();
}