package com.ta.pricemanagement.service;

import com.ta.pricemanagement.service.model.Pricing;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVService {

    public void upload(MultipartFile file);

    public List<Pricing> getProductPrices();
}
