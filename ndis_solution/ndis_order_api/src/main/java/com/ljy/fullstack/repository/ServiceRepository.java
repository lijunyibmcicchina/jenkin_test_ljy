package com.ljy.fullstack.repository;

import com.ljy.fullstack.pojo.NDISService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<NDISService, Long> {
    boolean existsByServiceName(String serviceName);
}
