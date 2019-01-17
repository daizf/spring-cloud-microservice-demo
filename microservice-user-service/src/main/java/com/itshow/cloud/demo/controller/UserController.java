package com.itshow.cloud.demo.controller;

import com.itshow.cloud.demo.dto.OrderDto;
import com.itshow.cloud.demo.dto.UserDto;
import com.itshow.cloud.demo.entity.User;
import com.itshow.cloud.demo.feign.OrderFeignClient;
import com.itshow.cloud.demo.repository.UserRepository;
import com.itshow.cloud.demo.dto.OrderDto;
import com.itshow.cloud.demo.dto.UserDto;
import com.itshow.cloud.demo.feign.OrderFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itshow.cloud.demo.entity.User;
import com.itshow.cloud.demo.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderFeignClient orderClient;

  @GetMapping("/{id}")
  public UserDto findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(findOne, userDto);

    List<OrderDto> orderDtos = orderClient.findByUserId(id);
    userDto.setOrders(orderDtos);

    return userDto;
  }
}
