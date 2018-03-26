package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Context extends Application {
	
	private BallFrame mBallFrame;
	
	

	
	
	@Override
	public void start(Stage stage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml"));
		
		BorderPane ballWindow = loader.load();
		
		mBallFrame = new BallFrame();
		ballWindow.setCenter(mBallFrame);	
		
		
		Scene window = new Scene(ballWindow, 1000, 600);
	    stage.setTitle("Assignment 4 - Bouncing Ball");
		stage.setScene(window);
		stage.show();
		
		setupPrimaryListeners(stage);
	}
	
	public BallFrame getBallFrame() {
		return mBallFrame;
	}
	
	public BallAnimation getAnimations() {
		return mBallFrame.getAnimations();
	}
	
	abstract void setupPrimaryListeners(Stage primaryStage);

	protected void exit() {
		System.exit(0);
	}
}
