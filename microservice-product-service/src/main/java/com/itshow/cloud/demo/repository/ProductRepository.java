package com.itshow.cloud.demo.repository;

import com.itshow.cloud.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itshow.cloud.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
