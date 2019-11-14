package com.coding.exam.tokigames.flight.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.dto.flight.BusinessFlights;
import com.coding.exam.tokigames.dto.flight.CheapFlights;
import com.coding.exam.tokigames.dto.result.PageResultDto;
import com.coding.exam.tokigames.flight.model.BusinessRoute;
import com.coding.exam.tokigames.flight.model.CheapRoute;
import com.coding.exam.tokigames.flight.rest.service.FlightRestService;
import com.coding.exam.tokigames.flight.service.FlightDataInitializerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class FlightDataInitializerServiceImpl implements FlightDataInitializerService {
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	private FlightRestService flightRestService;

	@Override
	public List<BaseFlightDto> initializeFlightSearchData() {
		CheapFlights cheapFlightsList = null;
		BusinessFlights businessFlightsList = null;
		List<BaseFlightDto> cheapFlightRoutes = null;
		List<BaseFlightDto> businessFlightRoutes = null;
		List<BaseFlightDto> allFlightRoutes = new ArrayList<>();
		
		if (StringUtils.isNotBlank(flightRestService.getCheapFlightRoutes())) {
			cheapFlightsList = gson.fromJson(flightRestService.getCheapFlightRoutes(), CheapFlights.class);
			cheapFlightsList.getData().forEach(CheapRoute::setBaseFlightDto);
			cheapFlightRoutes = cheapFlightsList.getData().stream().map(CheapRoute::getBaseFlightDto).collect(Collectors.toList());
			allFlightRoutes.addAll(cheapFlightRoutes);
		}
		
		if (StringUtils.isNotBlank(flightRestService.getBusinessFlightRoutes())) {
			businessFlightsList = gson.fromJson(flightRestService.getBusinessFlightRoutes(), BusinessFlights.class);
			businessFlightsList.getData().forEach(BusinessRoute::setBaseFlightDto);
			businessFlightRoutes = businessFlightsList.getData().stream().map(BusinessRoute::getBaseFlightDto).collect(Collectors.toList());
			allFlightRoutes.addAll(businessFlightRoutes);
		}
			
		return allFlightRoutes;
	}

	@Override
	public PageResultDto<BaseFlightDto> initializeFlightResultPage(List<BaseFlightDto> filteredFlightList,
			Integer pageNumber, Integer limit) {
		Integer totalResult = filteredFlightList.size();
		List<List<BaseFlightDto>> subSets = ListUtils.partition(filteredFlightList, limit);
		Integer totalPages = subSets.size();
		
		if (pageNumber <= totalPages) {
			return new PageResultDto<>(pageNumber, totalResult, subSets.get(pageNumber - 1), totalPages);
		} else {
			System.err.println("Page number is more than the total number of pages");
			return new PageResultDto<>();
		}
	}
}