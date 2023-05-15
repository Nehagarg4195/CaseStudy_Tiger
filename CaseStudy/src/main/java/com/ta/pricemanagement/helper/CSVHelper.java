package com.ta.pricemanagement.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import com.ta.pricemanagement.service.model.Pricing;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "store_id", "sku", "product_name", "price", "date" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Pricing> parseCSV(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Pricing> priceList = new ArrayList<Pricing>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");

            for (CSVRecord csvRecord : csvRecords) {
                Pricing price = new Pricing(
                        csvRecord.get("store_id"),
                        csvRecord.get("sku"),
                        csvRecord.get("product_name"),
                        Double.parseDouble(csvRecord.get("price")),
                        LocalDate.parse(csvRecord.get("date"),formatter));

                priceList.add(price);
            }

            return priceList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
