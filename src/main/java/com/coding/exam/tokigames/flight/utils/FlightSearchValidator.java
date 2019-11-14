package com.coding.exam.tokigames.flight.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.coding.exam.tokigames.constants.Constants;
import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;

public class FlightSearchValidator {
	private FlightSearchValidator() {
		
	}

	public static void isValidPlace(String place, StringBuilder sb) {
		if (StringUtils.isNotBlank(place) &&
				!StringUtils.isAlphaSpace(place)) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(place);
		}
	}
	
	public static void isValidDate(String date, StringBuilder sb) {
		try {
			LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			System.err.println("Invalid date format");
			
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(date);
		}
	}
	
	public static void isValidSortFilter(FlightSearchRequestDto flightSearchRequestDto,
			StringBuilder sb) {
		if (StringUtils.isNotBlank(flightSearchRequestDto.getSortBy()) &&
				!Constants.SORT_BY_NAMES.contains(flightSearchRequestDto.getSortBy())) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(flightSearchRequestDto.getSortBy());
		}
		
		if (StringUtils.isNotBlank(flightSearchRequestDto.getSortMethod()) &&
				!StringUtils.equalsIgnoreCase(Constants.ASC, flightSearchRequestDto.getSortMethod()) &&
				!StringUtils.equalsIgnoreCase(Constants.DESC, flightSearchRequestDto.getSortMethod())) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(flightSearchRequestDto.getSortMethod());
		} else if (StringUtils.isNotBlank(flightSearchRequestDto.getSortMethod()) &&
				StringUtils.isBlank(flightSearchRequestDto.getSortBy())) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(Constants.SORT_BY);
		}
	}
	
	public static void isValidPagination(Integer pageNumber,
			Integer limit, StringBuilder sb) {
		if (Objects.nonNull(limit) && limit <= 0) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(limit);
		}
		
		if (pageNumber <= 0) {
			if (StringUtils.isNotBlank(sb.toString())) {
				sb.append(", ");
			}
			
			sb.append(pageNumber);
		}
	}
}