package com.coding.exam.tokigames.flight.rest.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.coding.exam.tokigames.flight.rest.service.FlightRestService;

@Service
public class FlightRestServiceImpl implements FlightRestService {
	@Value("${cheap.flights.url}")
	private String cheapFlightsUrl;
	
	@Value("${business.flights.url}")
	private String businessFlightsUrl;
	
	@Override
	public String getCheapFlightRoutes() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		DefaultResponseErrorHandler handler = new DefaultResponseErrorHandler();
		restTemplate.setErrorHandler(handler);
		
		try {
			restTemplate.exchange(cheapFlightsUrl, HttpMethod.GET, null, String.class).getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getBusinessFlightRoutes() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		DefaultResponseErrorHandler handler = new DefaultResponseErrorHandler();
		restTemplate.setErrorHandler(handler);
		
		try {
			return restTemplate.exchange(businessFlightsUrl, HttpMethod.GET, null, String.class).getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}