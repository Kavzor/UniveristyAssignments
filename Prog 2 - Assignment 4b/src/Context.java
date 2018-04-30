import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Boots the bouncing ball application
 */
public class Context extends Application {
	private static final double WINDOW_WIDTH = 1000.00;
	private static final double WINDOW_HEIGHT = 600.00;
	private static final String WINDOW_TITLE = "Assignment 4 - Bouncing Ball";
	private static final String WINDOW_MAIN_FXML = "Window.fxml";
	
	private Interaction mController;
	

  /**
   * Launches the program
   * @param args
   */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		
		@SuppressWarnings("static-access")
		BorderPane ballWindow = loader.load(
				getClass().getResource(WINDOW_MAIN_FXML).openStream());
		
		mController = loader.getController();
		//BorderPane ballWindow = FXMLLoader.load();

		Scene window = new Scene(ballWindow, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setTitle(WINDOW_TITLE);
		stage.setScene(window);
		stage.show();

		
		stage.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
		stage.setOnCloseRequest(this::handleWindowRequest);
	}


  /**
   * Handles all windows requests such as minimizing and closing the window
   * @param event - The event performed
   */
	private void handleWindowRequest(WindowEvent event) {
		if(event.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST) {
			System.exit(0);
		}
	}

  /**
   * Handles global key events
   * @param event - The event performed
   */
	private void handleKey(KeyEvent event) {
		if(Keystroke.isWCtrlPressed(event)) {
			System.exit(0);
		}
		else if(Keystroke.isPCtrlPressed(event)) {
			mController.togglePlay();
		}
	}
}
