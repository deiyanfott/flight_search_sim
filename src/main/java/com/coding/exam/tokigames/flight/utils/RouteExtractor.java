package com.coding.exam.tokigames.flight.utils;

public class RouteExtractor {
	private RouteExtractor() {
		
	}
	
	public static String getOrigin(String route, boolean isOrigin) {
		String[] routeArray = route.split("-");
		return isOrigin ? routeArray[0] : routeArray[1];
	}
}