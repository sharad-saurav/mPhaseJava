package com.example.demo.repository;

import java.text.ParseException;
import java.util.List;

import com.example.demo.models.filters.Filter;
import com.example.demo.models.rest.StockQueryResult;

public interface StockCustomRepository {

	List<StockQueryResult> findByCustomFilter(Filter filter) throws ParseException;
}
