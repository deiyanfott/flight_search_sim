package com.coding.exam.tokigames.flight.utils.test;

import static org.junit.Assert.assertEquals;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.Test;

import com.coding.exam.tokigames.flight.utils.DateConverter;

public class DateConverterTest {
	@Test
	public void testLongToDateSuccess() {
		LocalDateTime date = DateConverter.convertLongToDate(Long.valueOf(1558902656));
		assertEquals(true, date instanceof LocalDateTime);
	}
	
	@Test(expected = DateTimeException.class)
	public void testLongToDateFailed() {
		DateConverter.convertLongToDate(Long.MAX_VALUE);
	}
	
	@Test
	public void testStringToDateSuccess() {
		LocalDateTime date = DateConverter.convertStringToDate("2019-11-11 12:00:00");
		assertEquals(true, date instanceof LocalDateTime);
	}
	
	@Test(expected = DateTimeException.class)
	public void testStringToDateNoTime() {
		DateConverter.convertStringToDate("2019-11-11");
	}
	
	@Test(expected = DateTimeException.class)
	public void testStringToDateWrongFormat() {
		DateConverter.convertStringToDate("2019/11/11 12:00:00");
	}
	
	@Test(expected = DateTimeException.class)
	public void testStringToDateWrongInput() {
		DateConverter.convertStringToDate("ABC");
	}
}