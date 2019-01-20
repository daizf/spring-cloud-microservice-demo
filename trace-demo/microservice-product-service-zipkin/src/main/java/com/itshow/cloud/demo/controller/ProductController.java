package com.itshow.cloud.demo.controller;

import com.itshow.cloud.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itshow.cloud.demo.entity.Product;
import com.itshow.cloud.demo.repository.ProductRepository;

@RestController
public class ProductController {
  @Autowired
  private ProductRepository userRepository;

  @GetMapping("/{id}")
  public Product findById(@PathVariable Long id) {
    Product findOne = this.userRepository.findOne(id);
    return findOne;
  }
}
