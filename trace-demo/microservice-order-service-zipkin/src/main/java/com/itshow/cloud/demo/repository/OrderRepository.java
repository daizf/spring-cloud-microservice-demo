package com.itshow.cloud.demo.repository;

import com.itshow.cloud.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itshow.cloud.demo.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.uid = :uid")
    List<Order> findByUserId(@Param("uid") Long uid);
}
