package com.courseplanner.driver;

import java.util.Arrays;

public class Driver {
	public static void main(String args[]) {
		CoursePlanner.add("CC101",0);
		CoursePlanner.add("CC101",0);
		CoursePlanner.add("CC101",0);
		CoursePlanner.add("CC101",0);
		CoursePlanner.add("CC101",0);
		CoursePlanner.add("CC102",0);
		System.out.println(Arrays.toString(CoursePlanner.schedule(0)));
	}
}
