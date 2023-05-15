package com.ta.pricemanagement.repository;

import com.ta.pricemanagement.service.model.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PricingRepository extends JpaRepository<Pricing, Long> {

    List<Pricing> findByStoreId(String storeId);


    Optional<Pricing> findById(Long Id);

    List<Pricing> findBySku(String sku);

    List<Pricing> findByProductName(String productName);

   // Pricing updateProduct(Long Id);


    @Modifying
    @Query("update Pricing p set p.price = :price  where p.id = :Id")
    Integer updatePriceById(Long Id, Double price);

}