package application;

import javafx.scene.input.KeyEvent;

public class Keystroke {
	
	public static boolean isWCtrlPressed(KeyEvent event) {
		return event.getCode().toString().toLowerCase().equals("w") && event.isControlDown();
	}

}
