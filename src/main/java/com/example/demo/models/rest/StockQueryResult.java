package com.example.demo.models.rest;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class StockQueryResult {

	Object aggregation;
	Double avgTotalTurnOver;
	Double avgNoOfShares;
}
