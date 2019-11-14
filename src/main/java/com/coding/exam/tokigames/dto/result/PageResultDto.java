package com.coding.exam.tokigames.dto.result;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDto<T> {
	private Integer pageNumber;
	private Integer totalResult;
	private List<T> list;
	private Integer totalPages;
}