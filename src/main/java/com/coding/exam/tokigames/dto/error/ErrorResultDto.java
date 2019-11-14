package com.coding.exam.tokigames.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResultDto {
	private String errorMessage;
	private String parameters;
}