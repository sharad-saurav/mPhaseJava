package com.example.demo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static LocalDateTime formatDate(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

		LocalDateTime formattedDate = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
		return formattedDate;
	}
	

}
