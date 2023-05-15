package com.ta.pricemanagement.service.impl;

import com.ta.pricemanagement.repository.PricingRepository;
import com.ta.pricemanagement.service.model.Pricing;
import com.ta.pricemanagement.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public Pricing savePricing(Pricing pricing) {
        return pricingRepository.save(pricing);
    }

    @Override
    public List<Pricing> findByStoreId(String storeId) {
        return pricingRepository.findByStoreId(storeId);
    }

    @Override
    public List<Pricing> findBySku(String sku) {
        return pricingRepository.findBySku(sku);
    }

    @Override
    public List<Pricing> findByProductName(String name) {
        return pricingRepository.findByProductName(name);
    }

    @Override
    public Pricing findById(Long Id) {
        Optional<Pricing> price = pricingRepository.findById(Id);
        return price.isPresent() ?  price.get() : null;
    }

    @Override
    public Integer updatePriceById(Long Id, Double price) {
        return pricingRepository.updatePriceById(Id,price);
    }

}

