package com.ljy.fullstack.pojo;

import jakarta.persistence.*;

@Entity
public class OrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderTypeId;

    @Column(nullable = false, unique = true)
    private String orderTypeName;

    private String description;

    public Long getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(long orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
