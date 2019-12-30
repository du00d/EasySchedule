package com.courseplanner.gui;

import com.courseplanner.driver.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;;

public class ScheduleFormat {
	File file;
	public ScheduleFormat() {
		try {
		file = new File("schedule.txt");
		if(!file.exists())
			file.createNewFile();
		}
		catch(Exception e) {
			System.out.println("Unable to create file");
		}
	}
	
	public void save() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			for(int i = 0 ; i < 7 ; i++) {
				if(CoursePlanner.schedule(i) == null)
					continue;
				for(String x : CoursePlanner.schedule(i)) {
					if(x == null)
						pw.print("Empty");
					else
						pw.print(x);
					pw.print("\n");
				}	
				pw.print("\n");
		}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to save");
		}
		
	}
}
