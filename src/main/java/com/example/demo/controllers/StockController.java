package com.example.demo.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.filters.Filter;
import com.example.demo.models.rest.StockQueryResult;
import com.example.demo.services.StockService;

@RestController
@RequestMapping(value = "/stocks")
public class StockController {

	
	@Autowired
	private StockService stockService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public List<StockQueryResult> findStocks(@RequestBody Filter filter) throws Exception {
		return stockService.getFilteredStocks(filter);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{date}/{price}", method = RequestMethod.PUT)
	public void updateStocks(@PathVariable("date") String date, @PathVariable("price") float price) throws ParseException {
	}
}
