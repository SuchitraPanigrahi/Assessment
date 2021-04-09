package com.sp.rbcassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.rbcassessment.model.Stock;
import com.sp.rbcassessment.repository.StockRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    // TO fetch the result from mongo based on stockId passed in the path variable
    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<List<Stock>> getStocksById(@PathVariable("stockId") String stockId) {
        List<Stock> stockData = stockRepository.findByStockId(stockId);
        if (stockData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stockData, HttpStatus.OK);
    }
    
    // To insert record with the data from the request body of POST
    @PostMapping("/insert")
    public ResponseEntity<Stock> createStock(@RequestBody Stock data) {
        try {
            Stock stock = stockRepository.save(new Stock(data.getQuarter(), data.getStockId(),
            		data.getDate(), data.getOpen(), data.getHigh(), data.getLow(),
            		data.getClose(), data.getVolume(), data.getPercent_change_price(),
            		data.getPercent_change_volume_over_last_wk(), data.getPrevious_weeks_volume(),
            		data.getNext_weeks_open(), data.getNext_weeks_close(),
            		data.getPercent_change_next_weeks_price(), data.getDays_to_next_dividend(),
            		data.getPercent_return_next_dividend()));
            return new ResponseEntity<>(stock,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
