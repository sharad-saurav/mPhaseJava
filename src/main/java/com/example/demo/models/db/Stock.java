package com.example.demo.models.db;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Document(collection = "stock")
public class Stock {
 
    @Id
    private ObjectId id;
    
    private Date date;
    
    private Float openPrice;
    
    private Float highPrice;
    
    private Float lowPrice;
    
    private Float closePrice;
    
    private Float wap;
    
    private Long noOfShares;
    
    private Long noOfTrades;
    
    private Long totalTurnOver;
    
    private Long deliverableQuantities;
    
    private Float quantitiesToTrade;
    
    private Float spreadHighLow;
    
    private Float spreadOpenClose;
    
    
}
