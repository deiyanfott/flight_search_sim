package com.coding.exam.tokigames.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {
	private Constants() {
		
	}
	
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String SORT_BY = "sortBy";
	public static final String SORT_METHOD = "sortMethod";
	public static final String PAGE_NUMBER = "pageNumber";
	public static final String LIMIT = "limit";
	
	private static List<String> filterParams = new ArrayList<>();
	static {
		filterParams.add(ORIGIN);
		filterParams.add(DESTINATION);
		filterParams.add(START_DATE);
		filterParams.add(END_DATE);
	}
	
	public static final List<String> SORT_BY_NAMES =  Collections.unmodifiableList(filterParams);
	
	public static final String ASC = "A";
	public static final String DESC = "D";
}