package com.itshow.cloud.demo.feign;

import com.itshow.cloud.demo.dto.ProductDto;
import com.itshow.cloud.demo.dto.ProductDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign的fallback测试
 * 使用@FeignClient的fallback属性指定回退类
 * @author 代志锋
 */
@FeignClient(name = "microservice-product-service" , fallback = ProductFallback.class)
public interface ProductFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  ProductDto findById(@PathVariable("id") Long id);
}

/**
 * 回退类FeignClientFallback需实现Feign Client接口
 * FeignClientFallback也可以是public class，没有区别
 * @author 代志锋
 */
@Component
class ProductFallback implements ProductFeignClient {
  @Override
  public ProductDto findById(Long id) {
    ProductDto productDto = new ProductDto();
    productDto.setId(-1L);
    productDto.setName("default product name");
    return productDto;
  }
}