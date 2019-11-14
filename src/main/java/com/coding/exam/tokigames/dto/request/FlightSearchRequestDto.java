package com.coding.exam.tokigames.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchRequestDto {
	private String origin;
	private String destination;
	private String startDate;
	private String endDate;
	private String sortBy;
	private String sortMethod;
	private Integer pageNumber;
	private Integer limit;
}