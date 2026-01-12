package com.ljy.fullstack.repository;

import com.ljy.fullstack.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 你必须手动加上这一行！
    // Spring Data JPA 会根据方法名自动生成 SQL：SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}
