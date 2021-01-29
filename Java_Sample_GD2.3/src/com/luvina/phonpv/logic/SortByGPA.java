package com.luvina.phonpv.logic;

import java.util.Comparator;

public class SortByGPA implements Comparator<Student> {
	@Override
	public int compare(Student student1, Student student2) {
		double x = Double.parseDouble(student1.getGpa());
		double y = Double.parseDouble(student2.getGpa());
		if (x - y > 0) {
			return -1;
		} else if (x - y < 0) {
			return 1;
		}
		return 0;

	}
}
