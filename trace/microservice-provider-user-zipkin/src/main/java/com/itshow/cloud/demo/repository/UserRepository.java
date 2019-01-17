package com.itshow.cloud.demo.repository;

import com.itshow.cloud.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itshow.cloud.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
