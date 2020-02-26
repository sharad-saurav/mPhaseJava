package com.example.demo.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.example.demo.models.db.Stock;
import com.example.demo.models.filters.Filter;
import com.example.demo.models.rest.StockQueryResult;
import com.example.demo.repository.StockCustomRepository;
import com.example.demo.utils.DateUtils;

@Repository
public class StockCustomRepositoryImpl implements StockCustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<StockQueryResult> findByCustomFilter(Filter filter) throws ParseException {
		LocalDateTime formattedFromDate = DateUtils.formatDate(filter.getFromDate());
		LocalDateTime formattedTodate = DateUtils.formatDate(filter.getToDate());

		switch (filter.getAggregation()) {
		case week:
			return mongoTemplate.aggregate(
					Aggregation.newAggregation(Aggregation.match(Criteria.where("date").gte(formattedFromDate)),
							Aggregation.match(Criteria.where("date").lte(formattedTodate)),

//							Aggregation.skip(filter.getPageSize() * filter.getPageNumber()),
//							Aggregation.limit(filter.getPageSize()),
							project("totalTurnOver", "noOfShares", "date").and(DateOperators.dateOf("date").isoWeek())
									.as("aggregation"),
							getGroupOperation(), project("avgTotalTurnOver", "avgNoOfShares", "aggregation"),
							sort(Sort.by("aggregation"))),
					Stock.class, StockQueryResult.class).getMappedResults();
		case day:
			return mongoTemplate.aggregate(
					Aggregation.newAggregation(Aggregation.match(Criteria.where("date").gte(formattedFromDate)),
							Aggregation.match(Criteria.where("date").lte(formattedTodate)),

//							Aggregation.skip(filter.getPageSize() * filter.getPageNumber()),
//							Aggregation.limit(filter.getPageSize()),
							project("totalTurnOver", "noOfShares", "date").and(DateOperators.dateOf("date").dayOfYear())
									.as("aggregation"),
							getGroupOperation(), project("avgTotalTurnOver", "avgNoOfShares", "aggregation"),
							sort(Sort.by("aggregation"))),
					Stock.class, StockQueryResult.class).getMappedResults();
		case month:
			return mongoTemplate.aggregate(
					Aggregation.newAggregation(Aggregation.match(Criteria.where("date").gte(formattedFromDate)),
							Aggregation.match(Criteria.where("date").lte(formattedTodate)),

//							Aggregation.skip(filter.getPageSize() * filter.getPageNumber()),
//							Aggregation.limit(filter.getPageSize()),
							project("totalTurnOver", "noOfShares", "date").and(DateOperators.dateOf("date").month())
									.as("aggregation"),
							getGroupOperation(), project("avgTotalTurnOver", "avgNoOfShares", "aggregation"),
							sort(Sort.by("aggregation"))),
					Stock.class, StockQueryResult.class).getMappedResults();
		default:
			return null;

		}

	}

	private GroupOperation getGroupOperation() {
		return group("aggregation").first("aggregation").as("aggregation").avg("totalTurnOver").as("avgTotalTurnOver")
				.avg("noOfShares").as("avgNoOfShares");
	}
}
