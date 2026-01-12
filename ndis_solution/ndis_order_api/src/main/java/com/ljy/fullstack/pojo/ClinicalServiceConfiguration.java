package com.ljy.fullstack.pojo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClinicalServiceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configId;

    @OneToOne
    @JoinColumn(name="order_id", nullable = false)
    private ServiceOrder serviceOrder;

    @Column(nullable = false)
    private Integer maxSessionPerWeek;

    @Column(nullable = false)
    private Boolean requiredDoctorApproval;

    @Column(nullable = false)
    private Integer therapyDurationMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Integer getMaxSessionPerWeek() {
        return maxSessionPerWeek;
    }

    public void setMaxSessionPerWeek(Integer maxSessionPerWeek) {
        this.maxSessionPerWeek = maxSessionPerWeek;
    }

    public Boolean getRequiredDoctorApproval() {
        return requiredDoctorApproval;
    }

    public void setRequiredDoctorApproval(Boolean requiredDoctorApproval) {
        this.requiredDoctorApproval = requiredDoctorApproval;
    }

    public Integer getTherapyDurationMinutes() {
        return therapyDurationMinutes;
    }

    public void setTherapyDurationMinutes(Integer therapyDurationMinutes) {
        this.therapyDurationMinutes = therapyDurationMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
