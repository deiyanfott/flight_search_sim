package com.coding.exam.tokigames.flight.service;

import java.util.List;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;

public interface FlightDataFilterService {
	public List<BaseFlightDto> filterFlightData(List<BaseFlightDto> allFlightRouteList,
			FlightSearchRequestDto flightSearchRequestDto, boolean noSort);
}