package com.ta.pricemanagement.controller;

import java.util.List;

import com.ta.pricemanagement.helper.CSVHelper;
import com.ta.pricemanagement.service.model.Pricing;
import com.ta.pricemanagement.service.model.ResponseMessage;
import com.ta.pricemanagement.service.CSVService;
import com.ta.pricemanagement.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class PricingController {

    @Autowired
    CSVService fileService;

    @Autowired
    PricingService priceService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        String message = "";
        System.out.println(name);
        if (CSVHelper.hasCSVFormat(file)) {
                fileService.upload(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/prices")
    public ResponseEntity<List<Pricing>> getProductPrices() {
        try {
            List<Pricing> priceList = fileService.getProductPrices();

            if (priceList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(priceList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/prices/{Id}")
    public ResponseEntity<Pricing> updatePrice(@PathVariable String Id, @RequestParam("price") String newPrice) {
            priceService.updatePriceById(Long.parseLong(Id), Double.parseDouble(newPrice));
            Pricing price = priceService.findById(Long.parseLong(Id));
            return new ResponseEntity<Pricing>(price, HttpStatus.OK);

    }

    @GetMapping("/sku/{Id}")
    public ResponseEntity<List<Pricing>> findBySKU(@PathVariable String Id) {
        List<Pricing> priceList = priceService.findBySku(Id);
        if (priceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Pricing>>(priceList, HttpStatus.OK);

    }

}

