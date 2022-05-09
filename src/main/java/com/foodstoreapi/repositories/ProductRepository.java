package com.foodstoreapi.repositories;

import com.foodstoreapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByFoodClass(int foodClass);
    List<Product> findByExpiry(String expiry);
}

