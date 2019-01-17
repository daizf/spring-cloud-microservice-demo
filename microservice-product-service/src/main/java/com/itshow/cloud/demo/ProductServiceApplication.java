package com.itshow.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 产品服务.
 * @author 代志锋
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }
}
