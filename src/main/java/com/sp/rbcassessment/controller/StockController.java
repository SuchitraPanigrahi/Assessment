package com.sp.rbcassessment.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sp.rbcassessment.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sp.rbcassessment.model.Stock;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @PostMapping("/uploadStocks")
    public ResponseEntity<?> uploadStocks(@RequestParam("file") MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<Stock> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Stock.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Stock> stocks = csvToBean.parse();
            stockRepository.saveAll(stocks);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStocks/{stockId}")
    public ResponseEntity<List<Stock>> getStocksById(@PathVariable("stockId") String stockId) {
        List<Stock> stockData = stockRepository.findByStock(stockId);
        if (stockData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stockData, HttpStatus.OK);
    }

    @PostMapping("/insertStock")
    public ResponseEntity<?> insertStock(@RequestBody Stock stock) {
        try {
            Stock insertedStock = stockRepository.save(stock);
            return new ResponseEntity<>(insertedStock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
