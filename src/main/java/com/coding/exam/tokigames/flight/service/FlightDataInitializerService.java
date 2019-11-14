package com.coding.exam.tokigames.flight.service;

import java.util.List;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.dto.result.PageResultDto;

public interface FlightDataInitializerService {
	public List<BaseFlightDto> initializeFlightSearchData();
	public PageResultDto<BaseFlightDto> initializeFlightResultPage(
			List<BaseFlightDto> filteredFlightList, Integer pageNumber, Integer limit);
}