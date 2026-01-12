package com.ljy.fullstack.pojo.dto;

public class ServiceTypeResponseDto {
    private Long serviceTypeId;

    private String serviceTypeName;

    private String description;

    public ServiceTypeResponseDto(Long serviceTypeId, String serviceTypeName, String description) {
        this.serviceTypeId = serviceTypeId;
        this.serviceTypeName = serviceTypeName;
        this.description = description;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
