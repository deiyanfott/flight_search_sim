package com.coding.exam.tokigames.flight.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.constants.APIConstants;
import com.coding.exam.tokigames.constants.ErrorMessages;
import com.coding.exam.tokigames.dto.error.ErrorResultDto;
import com.coding.exam.tokigames.dto.request.FlightSearchRequestDto;
import com.coding.exam.tokigames.dto.result.PageResultDto;
import com.coding.exam.tokigames.flight.service.FlightDataFilterService;
import com.coding.exam.tokigames.flight.service.FlightDataInitializerService;
import com.coding.exam.tokigames.flight.utils.FlightSearchValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping(APIConstants.API_ROOT)
public class FlightController {
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	private FlightDataInitializerService flightDataInitializerService;
	
	@Autowired
	private FlightDataFilterService flightDataFilterService;
	
	@GetMapping(value = APIConstants.FLIGHT_SEARCH,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllparcelList(
			@RequestParam(name = "origin") String origin,
			@RequestParam(name = "destination") String destination,
			@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "sortMethod", required = false) String sortMethod,
			@RequestParam(name = "pageNumber") Integer pageNumber,
			@RequestParam(name = "limit", required = false) Integer limit) {
		FlightSearchRequestDto flightSearchRequestDto = new FlightSearchRequestDto(
				origin, destination, startDate, endDate, sortBy, sortMethod, pageNumber, limit);
		StringBuilder sb = new StringBuilder();
		boolean noSort = false;
		
		FlightSearchValidator.isValidPlace(origin, sb);
		FlightSearchValidator.isValidPlace(destination, sb);
		FlightSearchValidator.isValidDate(startDate, sb);
		FlightSearchValidator.isValidDate(endDate, sb);
		FlightSearchValidator.isValidSortFilter(flightSearchRequestDto, sb);
		FlightSearchValidator.isValidPagination(pageNumber, limit, sb);
		
		if (StringUtils.isBlank(sb.toString())) {
			List<BaseFlightDto> result = flightDataInitializerService.initializeFlightSearchData();
			
			if (CollectionUtils.isEmpty(result)) {
				return new ResponseEntity<>(gson.toJson(new PageResultDto<>()), HttpStatus.OK);
			} else {
				noSort = StringUtils.isBlank(sortBy) && StringUtils.isBlank(sortMethod) && Objects.isNull(limit);
				List<BaseFlightDto> filteredFlightList = flightDataFilterService.filterFlightData(
							result, flightSearchRequestDto, noSort);
				limit = Objects.isNull(limit) ? 1 : limit;
				
				if (CollectionUtils.isNotEmpty(filteredFlightList)) {
					return new ResponseEntity<>(
							gson.toJson(flightDataInitializerService
									.initializeFlightResultPage(filteredFlightList, pageNumber, limit)),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<>(gson.toJson(new PageResultDto<>()), HttpStatus.OK);
				}
			}
		} else {
			ErrorResultDto errorResultDto = new ErrorResultDto();
			errorResultDto.setErrorMessage(ErrorMessages.INVALID_INPUT_PARAMETERS);
			errorResultDto.setParameters(sb.toString());
			return new ResponseEntity<>(gson.toJson(errorResultDto), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}