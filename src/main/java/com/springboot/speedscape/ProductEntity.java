package com.springboot.speedscape;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class ProductEntity {
   
    private @Id @GeneratedValue Long id_product;
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;

    protected ProductEntity() {
        this(null, null);
    }

    ProductEntity(String name, String description) {
        this.id_product = null;
        this.name = name;
        this.description = description;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
