package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.db.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, Long>{
	
	List<Stock> findByDate(LocalDateTime localDateTime);	

}
