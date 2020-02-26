package com.example.demo.models.filters;

import com.example.demo.constants.Constants.Aggregation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Filter {

	private String fromDate;
	private String toDate;
	private int pageNumber = 1;
	private int pageSize = 10;
	private Aggregation aggregation;
	
}
