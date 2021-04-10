package com.sp.rbcassessment.repository;

import com.sp.rbcassessment.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock, String> {
    List<Stock> findByStock(String stock);
}
