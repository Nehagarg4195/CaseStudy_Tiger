package com.ta.pricemanagement.service.impl;

import java.io.IOException;
import java.util.List;

import com.ta.pricemanagement.repository.PricingRepository;
import com.ta.pricemanagement.helper.CSVHelper;
import com.ta.pricemanagement.service.model.Pricing;
import com.ta.pricemanagement.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CSVServiceImpl implements CSVService {
    @Autowired
    PricingRepository repository;

    public void upload(MultipartFile file) {
        try {
            List<Pricing> prices = CSVHelper.parseCSV(file.getInputStream());
            repository.saveAll(prices);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse and store csv repository: " + e.getMessage());
        }
    }

    public List<Pricing> getProductPrices() {
        return repository.findAll();
    }
}
