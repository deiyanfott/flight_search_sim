package com.coding.exam.tokigames.flight.model;

import com.coding.exam.tokigames.base.dto.BaseFlightDto;
import com.coding.exam.tokigames.base.model.BaseRoute;
import com.coding.exam.tokigames.flight.utils.DateConverter;
import com.coding.exam.tokigames.flight.utils.RouteExtractor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(includeFieldNames = true, callSuper = true)
public class CheapRoute extends BaseRoute<Long> {
	@Setter
	private String route;
	private BaseFlightDto baseFlightDto;
	
	@Override
	public void setBaseFlightDto() {
		baseFlightDto = new BaseFlightDto(
				RouteExtractor.getOrigin(getRoute(), true),
				RouteExtractor.getOrigin(getRoute(), false),
				DateConverter.convertLongToDate(getDeparture()),
				DateConverter.convertLongToDate(getArrival()));
	}
}