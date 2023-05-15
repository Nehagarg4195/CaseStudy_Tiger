package com.ta.pricemanagement.service;

import com.ta.pricemanagement.service.model.Pricing;

import java.util.List;

public interface PricingService {

    Pricing savePricing(Pricing pricing);

    List<Pricing> findByStoreId(String storeId);

    List<Pricing> findBySku(String sku);

    List<Pricing> findByProductName(String name);

    Pricing findById(Long Id);

    Integer updatePriceById(Long Id, Double price);

}