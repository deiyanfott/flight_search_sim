package com.coding.exam.tokigames.base.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(includeFieldNames = true)
public abstract class BaseRoute<T> {
	@Setter
	private T departure;
	@Setter
	private T arrival;
	public abstract void setBaseFlightDto();
}