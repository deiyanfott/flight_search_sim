package com.coding.exam.tokigames.flight.utils.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;
import com.coding.exam.tokigames.flight.utils.FlightSearchValidator;

public class FlightSearchValidatorTest {
	private StringBuilder sb;
	private FlightSearchRequestDto dto;
	
	@Before
	public void setUp() {
		sb = new StringBuilder();
	}
	
	@Test
	public void testIsValidPlaceNoSpaceSuccess() {
		FlightSearchValidator.isValidPlace("asrba", sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceWithSpaceSuccess() {
		FlightSearchValidator.isValidPlace("a srb a", sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceNoValueSuccess() {
		FlightSearchValidator.isValidPlace("", sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceNullSuccess() {
		FlightSearchValidator.isValidPlace(null, sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceWhitespaceOnlySuccess() {
		FlightSearchValidator.isValidPlace("           ", sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceWithNumberFailed() {
		FlightSearchValidator.isValidPlace("asrb1a", sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPlaceWithSpecialCharFailed() {
		FlightSearchValidator.isValidPlace("asrb-a", sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidDateWithTimeFailed() {
		FlightSearchValidator.isValidDate("2019-11-11 12:00:00", sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidDateOnlySuccess() {
		FlightSearchValidator.isValidDate("2019-11-11", sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidDateWrongFormat() {
		FlightSearchValidator.isValidDate("2019/11/11", sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidDateWrongInput() {
		FlightSearchValidator.isValidDate("ABC", sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidSortFilterSuccess() {
		dto = new FlightSearchRequestDto();
		dto.setSortBy("origin");
		dto.setSortMethod("A");
		FlightSearchValidator.isValidSortFilter(dto, sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidSortFilterWrongSortByFailed() {
		dto = new FlightSearchRequestDto();
		dto.setSortBy("test");
		dto.setSortMethod("A");
		FlightSearchValidator.isValidSortFilter(dto, sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidSortFilterWrongSortMethodFailed() {
		dto = new FlightSearchRequestDto();
		dto.setSortBy("origin");
		dto.setSortMethod("X");
		FlightSearchValidator.isValidSortFilter(dto, sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidSortFilterWithValidSortMethodEmptySortByFailed() {
		dto = new FlightSearchRequestDto();
		dto.setSortBy("");
		dto.setSortMethod("A");
		FlightSearchValidator.isValidSortFilter(dto, sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPaginationSuccess() {
		FlightSearchValidator.isValidPagination(1, 1, sb);
		assertEquals(true, StringUtils.isBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPaginationZeroLimit() {
		FlightSearchValidator.isValidPagination(1, 0, sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
	
	@Test
	public void testIsValidPaginationZeroPageNumber() {
		FlightSearchValidator.isValidPagination(0, 1, sb);
		assertEquals(true, StringUtils.isNotBlank(sb.toString()));
	}
}