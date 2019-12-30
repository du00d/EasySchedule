package com.courseplanner.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application{
	
	public static void main(String args[]) {
		launch(args);
	}
	
	public void start(Stage arg0) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
		arg0.setTitle("Course Planner");
		arg0.setScene(new Scene(root, 1000, 450));
		arg0.show();		
	}
		
}
