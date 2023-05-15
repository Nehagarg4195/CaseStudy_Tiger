package com.ta.pricemanagement.service.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pricing")
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private LocalDate date;

    public Pricing(String storeId, String sku, String productName, double price, LocalDate date) {
        this.storeId = storeId;
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.date = date;
    }

    public Pricing() {

    }

    public Long getId() {
        return id;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
