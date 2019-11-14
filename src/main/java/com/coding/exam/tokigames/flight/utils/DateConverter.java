package com.coding.exam.tokigames.flight.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {
	private DateConverter() {
		
	}
	
	public static LocalDateTime convertLongToDate(Long millis) {
		return LocalDateTime.ofInstant(
				Instant.ofEpochSecond(millis), ZoneId.systemDefault());
	}
	
	public static LocalDateTime convertStringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(date, formatter);
	}
}