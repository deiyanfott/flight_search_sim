package com.coding.exam.tokigames.flight.service.impl;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.base.service.BaseCompartorService;
import com.coding.exam.tokigames.constants.Constants;

@Service
public class FlightDateComparatorServiceImpl implements BaseCompartorService<BaseFlightDto> {
	@Override
	public Comparator<BaseFlightDto> getComparator(String sortBy) {
		switch (sortBy) {
			case Constants.START_DATE :
				return (a, b) -> a.getStartDate().compareTo(b.getStartDate());
			case Constants.END_DATE :
				return (a, b) -> a.getEndDate().compareTo(b.getEndDate());
			default: return null;
		}
	}
}