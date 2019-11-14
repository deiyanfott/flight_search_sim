package com.coding.exam.tokigames.flight.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.base.service.BaseCompartorService;
import com.coding.exam.tokigames.constants.Constants;
import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;
import com.coding.exam.tokigames.flight.service.FlightDataFilterService;
import com.coding.exam.tokigames.flight.utils.DateConverter;

@Service
public class FlightDataFilterServiceImpl implements FlightDataFilterService {
	@Autowired
	private BaseCompartorService<BaseFlightDto> comparatorService;
	
	@Override
	public List<BaseFlightDto> filterFlightData(List<BaseFlightDto> allFlightRouteList,
			FlightSearchRequestDto flightSearchRequestDto, boolean noSort) {
		LocalDateTime dateStartParamReq = DateConverter.convertStringToDate(
				flightSearchRequestDto.getStartDate() + " 00:00:00");
		LocalDateTime dateEndParamReq = DateConverter.convertStringToDate(
				flightSearchRequestDto.getEndDate() + " 23:59:59");
		
		List<BaseFlightDto> filterList = allFlightRouteList.stream()
				.filter(allFlightDto -> StringUtils.equalsIgnoreCase(
						allFlightDto.getOrigin(), flightSearchRequestDto.getOrigin()) &&
						StringUtils.equalsIgnoreCase(
								allFlightDto.getDestination(), flightSearchRequestDto.getDestination()) &&
						 allFlightDto.getStartDate().compareTo(dateStartParamReq) >= 0 &&
						 allFlightDto.getEndDate().compareTo(dateEndParamReq) <= 0)
				.collect(Collectors.toList());
		
		if (StringUtils.isNotBlank(flightSearchRequestDto.getSortBy()) && !noSort) {
			Comparator<BaseFlightDto> comparator = comparatorService.getComparator(flightSearchRequestDto.getSortBy());
			
			if (Objects.nonNull(comparator) &&
					StringUtils.isNotBlank(flightSearchRequestDto.getSortMethod())) {
				sortFlight(filterList, comparator, flightSearchRequestDto.getSortMethod());
			}
		}
		
		return filterList;
	}
	
	private void sortFlight(List<BaseFlightDto> filterList,
			Comparator<BaseFlightDto> comparator, String sortmethod) {
		filterList.sort(comparator);
		
		if (StringUtils.equalsIgnoreCase(Constants.DESC, sortmethod)) {
			Collections.reverse(filterList);
		}
	}
}