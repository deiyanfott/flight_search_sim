package com.coding.exam.tokigames.flight.model;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.base.model.BaseRoute;
import com.coding.exam.tokigames.flight.utils.DateConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(includeFieldNames = true, callSuper = true)
public class BusinessRoute extends BaseRoute<String> {
	@Setter
	private Long departureTime;
	@Setter
	private Long arrivalTime;
	private BaseFlightDto baseFlightDto;
	
	@Override
	public void setBaseFlightDto() {
		baseFlightDto = new BaseFlightDto(
				getDeparture(), getArrival(),
				DateConverter.convertLongToDate(getDepartureTime()),
				DateConverter.convertLongToDate(getArrivalTime()));
	}
}