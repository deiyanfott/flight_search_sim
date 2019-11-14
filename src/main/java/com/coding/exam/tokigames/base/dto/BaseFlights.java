package com.coding.exam.tokigames.base.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(includeFieldNames = true)
public class BaseFlights<T> {
	private List<T> data;
}