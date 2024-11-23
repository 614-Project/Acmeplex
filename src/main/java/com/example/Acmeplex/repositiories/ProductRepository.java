package com.example.Acmeplex.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Acmeplex.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}