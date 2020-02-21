package com.courseplanner.driver;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HubUnitParser {
	private int totalNumberOfCoursesWithHeader = 0;
	private String[] categories = {"Philosophical","Aesthetic","Historical","Scientific1","Scientific2","Social1","Social2","Quantitative1","Quantitative2",
			"Individual","Global","Ethical","FYW","WRI","WIC","Oral","Digital","Critical","Research","Teamwork","Creativity"};
	private int[] requirements = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2};
	private int credit = 0;
	private ArrayList<String> list;
	
	
	public HubUnitParser(){
		list = new ArrayList<String>();
		try(Scanner sc = new Scanner(new File("buhub.csv"))){
			sc.useDelimiter("[,\n]");
			while(sc.hasNext()) {
				totalNumberOfCoursesWithHeader++;
				list.add(sc.next());
			}
			totalNumberOfCoursesWithHeader /o= 24;
			sc.close();
		}catch(Exception e) {
			System.out.println("Missing File: \"buhub.csv\"");
		}
	}
	
	public int[] addCourse(String course) {
		int index = 0;
		int[] hubunits = new int[22];
		while(!list.get(index).equals(course)) {
			index += 24;
		}
		for(int i = 0 ; i < hubunits.length ; i++) {
			hubunits[i] = Integer.parseInt(list.get(index + 1 + i));
		}
		for(int s = 0; s < requirements.length ; s++) {
			requirements[s] -= hubunits[s];
		}
		
		credit += hubunits[21];
		
		return hubunits;
	}
	
	
	public void removeCourse(String course) {
		int index = 0;
		int[] hubunits = new int[22];
		while(!list.get(index).equals(course)) {
			index += 24;
		}
		for(int i = 0 ; i < hubunits.length; i++) {
			hubunits[i] = Integer.parseInt(list.get(index + 1 + i));
		}
		for(int s = 0 ; s < requirements.length ; s++) {
			requirements[s] += hubunits[s];
		}
		credit -= hubunits[21];
	}
	
	public String printInfo() {
		String remaining = "";
		System.out.println("Remaining Units:");
		for(int i = 0 ; i < categories.length ;i++) {
			if(requirements[i] != 0)
				remaining += categories[i] + " "+ requirements[i] + "\n"; 
		}
		remaining += "Total Credit: " + credit;
		return remaining;
	}
	
	public String unitInfo(String course) {
		int index = 0;
		while(!list.get(index).equals(course)) {
			index += 24;
		}
		String cred = list.get(index + 22);
		String info = course + " " + cred + " credits \n";
		
		for(int i = 0 ; i < 21 ; i++) {
			if(Integer.parseInt(list.get(index + 1 + i)) != 0) {
				info += categories[i] + " " + list.get(index + 1 + i) + "\n";
			}
		}
		
		return info;	
	}
	
	public int[] toBeCompleted() {
		return requirements;
	}
	
	public String[] allCourses() {
		String[] courses = new String[100];
		int index = 0;
		for(int i = 0; i < courses.length ; i++) {
			courses[i] = list.get(index);
			index += 24;
		}
		
		return courses;
	}
	
	public ArrayList<String> listofCourses() {
		ArrayList<String> courseOnlyList = new ArrayList<String>();
		int i = 24;
		while(i < totalNumberOfCoursesWithHeader * 24) {
		courseOnlyList.add(list.get(i));
		i += 24;
		}
		return courseOnlyList;
	}
}
