package com.itshow.cloud.demo.controller;

import com.itshow.cloud.demo.dto.OrderDto;
import com.itshow.cloud.demo.dto.ProductDto;
import com.itshow.cloud.demo.entity.Order;
import com.itshow.cloud.demo.feign.ProductFeignClient;
import com.itshow.cloud.demo.dto.OrderDto;
import com.itshow.cloud.demo.feign.ProductFeignClient;
import com.itshow.cloud.demo.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itshow.cloud.demo.entity.Order;
import com.itshow.cloud.demo.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductFeignClient productClient;

  @GetMapping("/{id}")
  public Order findById(@PathVariable Long id) {
    Order findOne = this.orderRepository.findOne(id);
    return findOne;
  }

  @GetMapping("user/{id}")
  public List<OrderDto> findByUserId(@PathVariable Long id) {
    List<Order> orders = this.orderRepository.findByUserId(id);
    List<OrderDto> orderDtos = new ArrayList<>();
    for (Order order : orders) {
      OrderDto orderDto = new OrderDto();
      BeanUtils.copyProperties(order, orderDto);
      Long pid = order.getPid();
      ProductDto productDto = productClient.findById(pid);
      orderDto.setProduct(productDto);

      orderDtos.add(orderDto);
    }

    return orderDtos;
  }
}
