import javafx.scene.input.KeyEvent;
/**
 * Verifies any Keystrokes handled and performed by the user
 * @author Jakob
 *
 */
public class Keystroke {
	/**
	 * Checks if a keyevent matches with ctrl + w
	 * @param event - The event
	 * @return  - Whatever ctrl + w was pressed or not
	 */
	public static boolean isWCtrlPressed(KeyEvent event) {
		return event.getCode().toString().toLowerCase().equals("w") && event.isControlDown();
	}
	
	public static boolean isPCtrlPressed(KeyEvent event) {
		return event.getCode().toString().toLowerCase().equals("p") && event.isControlDown();
	}
}
