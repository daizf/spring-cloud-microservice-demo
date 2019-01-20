package com.itshow.cloud.demo.feign;

import com.itshow.cloud.demo.dto.OrderDto;
import com.itshow.cloud.demo.dto.OrderDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Feign的fallback测试
 * 使用@FeignClient的fallback属性指定回退类
 * @author 代志锋
 */
@FeignClient(name = "microservice-order-service", fallback = OrderFallback.class)
public interface OrderFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  OrderDto findById(@PathVariable("id") Long id);

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  List<OrderDto> findByUserId(@PathVariable("id") Long id);
}

/**
 * 回退类FeignClientFallback需实现Feign Client接口
 * FeignClientFallback也可以是public class，没有区别
 * @author 代志锋
 */
@Component
class OrderFallback implements OrderFeignClient {

  @Override
  public OrderDto findById(Long id) {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(-1L);
    return orderDto;
  }

  @Override
  public List<OrderDto> findByUserId(Long id) {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(-1L);
    List<OrderDto> orderDtos = new ArrayList<>(1);
    orderDtos.add(orderDto);
    return orderDtos;
  }
}