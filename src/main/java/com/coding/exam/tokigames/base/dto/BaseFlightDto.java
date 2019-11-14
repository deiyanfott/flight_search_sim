package com.coding.exam.tokigames.base.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseFlightDto {
	private String origin;
	private String destination;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}