package com.coding.exam.tokigames.flight.service.impl.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.dto.flight.BusinessFlights;
import com.coding.exam.tokigames.dto.flight.CheapFlights;
import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;
import com.coding.exam.tokigames.dto.result.PageResultDto;
import com.coding.exam.tokigames.flight.model.BusinessRoute;
import com.coding.exam.tokigames.flight.model.CheapRoute;
import com.coding.exam.tokigames.flight.service.FlightDataFilterService;
import com.coding.exam.tokigames.flight.service.FlightDataInitializerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightDataServiceImplTest {
	@Autowired
	private FlightDataFilterService flightDataFilterService;
	
	@Autowired
	private FlightDataInitializerService flightDataInitializerService;
	
	private FlightSearchRequestDto dto;
	
	private static CheapFlights cheapFlightsList = null;
	private static BusinessFlights businessFlightsList = null;
	private static List<BaseFlightDto> allFlightRoutes = new ArrayList<>();
	private static final File cheapFlightsFile = new File(FlightDataFilterService.class.getClassLoader().getResource("cheap_flights.json").getFile());
	private static final File businessFlightsFile = new File(FlightDataFilterService.class.getClassLoader().getResource("business_flights.json").getFile());
	
	@BeforeClass
	public static void setup() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (Reader cheapFlightsReader = new FileReader(cheapFlightsFile.getAbsolutePath());
				Reader businessFlightsReader = new FileReader(businessFlightsFile.getAbsolutePath());) {
			cheapFlightsList = gson.fromJson(cheapFlightsReader, CheapFlights.class);
			cheapFlightsList.getData().forEach(CheapRoute::setBaseFlightDto);
			List<BaseFlightDto> cheapFlightRoutes = cheapFlightsList.getData().stream().map(CheapRoute::getBaseFlightDto).collect(Collectors.toList());
			allFlightRoutes.addAll(cheapFlightRoutes);
			
			businessFlightsList = gson.fromJson(businessFlightsReader, BusinessFlights.class);
			businessFlightsList.getData().forEach(BusinessRoute::setBaseFlightDto);
			List<BaseFlightDto> businessFlightRoutes = businessFlightsList.getData().stream().map(BusinessRoute::getBaseFlightDto).collect(Collectors.toList());
			allFlightRoutes.addAll(businessFlightRoutes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFilterFlightDataNoSort() {
		dto = new FlightSearchRequestDto();
		dto.setOrigin("Istanbul");
		dto.setDestination("Antalya");
		dto.setStartDate("2017-01-01");
		dto.setEndDate("2019-10-10");
		dto.setPageNumber(1);
		
		List<BaseFlightDto> filteredList = flightDataFilterService
				.filterFlightData(allFlightRoutes, dto, true);
		assertEquals(2, filteredList.size());
	}
	
	@Test
	public void testFilterFlightDataWithLimit() {
		dto = new FlightSearchRequestDto();
		dto.setOrigin("Istanbul");
		dto.setDestination("Antalya");
		dto.setStartDate("2017-01-01");
		dto.setEndDate("2019-10-10");
		
		List<BaseFlightDto> filteredFlightList = flightDataFilterService
				.filterFlightData(allFlightRoutes, dto, true);
		PageResultDto<BaseFlightDto> result = flightDataInitializerService
				.initializeFlightResultPage(filteredFlightList, 1, 1);
		assertEquals(2, result.getTotalPages().intValue());
	}
	
	@Test
	public void testFilterFlightDataPageNumberExceedsTotalPages() {
		dto = new FlightSearchRequestDto();
		dto.setOrigin("Istanbul");
		dto.setDestination("Antalya");
		dto.setStartDate("2017-01-01");
		dto.setEndDate("2019-10-10");
		
		List<BaseFlightDto> filteredFlightList = flightDataFilterService
				.filterFlightData(allFlightRoutes, dto, true);
		PageResultDto<BaseFlightDto> result = flightDataInitializerService
				.initializeFlightResultPage(filteredFlightList, 2, 2);
		assertEquals(true, CollectionUtils.isEmpty(result.getList()));
	}
}