package com.courseplanner.driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import com.courseplanner.ds.OpenHashTable;

public class CoursePlanner {
	@SuppressWarnings("unchecked")
	private static OpenHashTable[] table = new OpenHashTable[7];
	private static HubUnitParser parser = new HubUnitParser();
	public static void add(String course, int semester) {
		if(semester > 7) {
			throw new IllegalArgumentException();
		}
		if(table[semester] == null) {
			table[semester] = new OpenHashTable(5,0);
		}
		table[semester].insert(course, course);
		parser.addCourse(course);
	}
	
	public static void remove(String course, int semester) {
		if(table[semester] == null) {
			return;
		}
		table[semester].remove(course);
		parser.removeCourse(course);

	}
	
	public static String[] schedule(int semester) {
		if(table[semester] == null) {
			return null;
		}
		return table[semester].printAll();
	}
	
	public static int[] toBeCompleted() {
		return parser.toBeCompleted();
	}
	
	public static String[] allCourses() {
		return parser.allCourses();
	}
	
	public static ArrayList courseList() {
		return parser.listofCourses();
	}
}
