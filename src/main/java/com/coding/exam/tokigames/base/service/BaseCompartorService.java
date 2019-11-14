package com.coding.exam.tokigames.base.service;

import java.util.Comparator;

public interface BaseCompartorService<T> {
	public Comparator<T> getComparator(String sortBy);
}