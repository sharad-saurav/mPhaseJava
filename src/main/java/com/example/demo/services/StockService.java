package com.example.demo.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.db.Stock;
import com.example.demo.models.filters.Filter;
import com.example.demo.models.rest.StockQueryResult;
import com.example.demo.repository.StockCustomRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.utils.DateUtils;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockCustomRepository stockCustomRepository;
	
	
	public void updateStockPrice(String date, float newPrice) throws ParseException {
		
		List<Stock> stocks = stockRepository.findByDate(DateUtils.formatDate(date));
		
		if (!CollectionUtils.isEmpty(stocks)) {
			stocks.stream().forEach(s -> {
				s.setOpenPrice(newPrice);
			});
			
			stockRepository.saveAll(stocks);
		}
		
	}
	
	public List<StockQueryResult> getFilteredStocks(Filter filter) throws ParseException {
		List<StockQueryResult> result = stockCustomRepository.findByCustomFilter(filter);
		if (filter.getPageSize()*filter.getPageNumber() < result.size()) {
			
			int endIndex = filter.getPageSize()*(filter.getPageNumber()+1) < result.size() ? filter.getPageSize()*(filter.getPageNumber()+1) : result.size();
			
			return result.subList(filter.getPageSize()*filter.getPageNumber(), endIndex);
		}
		return new ArrayList<>();
	}
}
