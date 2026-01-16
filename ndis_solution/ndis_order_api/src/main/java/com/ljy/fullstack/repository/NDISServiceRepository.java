package com.ljy.fullstack.repository;

import com.ljy.fullstack.pojo.NDISService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NDISServiceRepository extends JpaRepository<NDISService, Long> {
    List<NDISService> findByServiceType_ServiceTypeId(Long serviceTypeId);
}
