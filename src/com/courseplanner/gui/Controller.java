package com.courseplanner.gui;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.courseplanner.driver.CoursePlanner;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

public class Controller implements Initializable{
	@FXML private ListView<String> list = new ListView<String>();
	@FXML private TextArea main;
	@FXML private TextArea ta1 = new TextArea();
	@FXML private TextArea ta12 = new TextArea();
	@FXML private TextArea ta2 = new TextArea();
	@FXML private TextArea ta22 = new TextArea();
	@FXML private TextArea ta3 = new TextArea();
	@FXML private TextArea ta32 = new TextArea();
	@FXML private TextArea ta4 = new TextArea();
	@FXML private TextArea ta42 = new TextArea();
	@FXML private TextArea Hub = new TextArea();
	private ListProperty<String> listProperty = new SimpleListProperty<>();
	private int semester = 0;
	
	String[] hubunits = {"Philosophical","Aesthetic","Historical","Scientific I","Scientific II","Social I","Social II","Quantitative I","Quantitative II",
			"Individual","Global","Ethical","FYW","WRI","WIC","Oral","Digital","Critical","Research","Teamwork","Creativity"};
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list.itemsProperty().bind(listProperty);
		listProperty.set(FXCollections.observableArrayList(CoursePlanner.courseList()));
		main = ta1;
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	
	public void add() {
		ObservableList listofItems = list.getSelectionModel().getSelectedItems();
		String textArea = "";
		try {
		for(Object item : listofItems) {
			CoursePlanner.add((String)item, semester);
		}
		
		for(String text : CoursePlanner.schedule(semester)) {
			if(text == null)
				break;
			textArea += text + "\n";
		}
		}catch(Exception e) {
		}
		this.main.setText(textArea);
		String hubTextArea = "";
		int i = 0;
		for(String x : hubunits) {
			hubTextArea += x + " " + CoursePlanner.toBeCompleted()[i] + "\n";
			i++;
		}
		Hub.setText(hubTextArea);
		
	}
	
	public void remove() {
		String textArea = "";
		ObservableList listofItems = list.getSelectionModel().getSelectedItems();
		for(Object item : listofItems) {
			if(item == null)
				break;
			CoursePlanner.remove((String)item, semester);
		}	
		for(String text : CoursePlanner.schedule(semester)) {
			if(text != null)
			textArea += text + "\n";
		}
		this.main.setText(textArea);
		String hubTextArea = "";
		int i = 0;
		for(String x : hubunits) {
			hubTextArea += x + " " + CoursePlanner.toBeCompleted()[i] + "\n";
			i++;
		}
		Hub.setText(hubTextArea);
	}
	public void freshmanChange() {
		main = ta1;
	}
	public void sophomoreChange() {
		main = ta2;
		semester = 2;
	}
	public void juniorChange() {
		main = ta3;
		semester = 4;
	}
	public void seniorChange() {
		main = ta4;
		semester = 6;
	}
	public void freshman2Change() {
		main = ta12;
		semester = 1;
	}
	public void sophomore2Change() {

		main = ta22;
		semester = 3;
	}
	public void junior2Change() {
		main = ta32;
		semester = 5;
	}
	public void senior2Change() {
		main = ta42;
		semester = 7;
	}
	
	public void save() {
		ScheduleFormat sc = new ScheduleFormat();
		sc.save();
	}
}
