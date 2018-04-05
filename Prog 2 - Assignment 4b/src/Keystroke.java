import javafx.scene.input.KeyEvent;

public class Keystroke {
	/**
	 * Checks if a keyevent matches with ctrl + w
	 * @param event - The event
	 * @return  - Whatever ctrl + w was pressed or not
	 */
	public static boolean isWCtrlPressed(KeyEvent event) {
		return event.getCode().toString().toLowerCase().equals("w") && event.isControlDown();
	}
}
