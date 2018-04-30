import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Handles all the interaction of the GUI and distributes them to the respective controller
 */
public class Interaction extends AbstractInteraction {	
	
	/**
	 * Handles any click events invoked within the ball frame
	 * @param event - The event performed
	 */
	public void handleFrameClick(MouseEvent event) {
		getBallController().generateFrom(getFrameAction(), event);
	}

	/** file **/
	public void setClickAddBall() {
		setFrameAction(FrameAction.CLICK_ADD_BALL);
	}

	public void setClickRemoveBall() {
		setFrameAction(FrameAction.CLICK_REMOVE_BALL);
	}

	public void setClickAddBlackBox() {
		setFrameAction(FrameAction.CLICK_ADD_BLACKBOX);
	}

	public void setClickRemoveBlackBox() {
		setFrameAction(FrameAction.CLICK_REMOVE_BLACKBOX);
	}

	public void shutdown() {
		getSystemController().shutdown();
	}

	public void clearField() {
		getBallController().getWorld().clear();
	}

	/** animation **/
	
	public void togglePlay() {
		getAnimationController().togglePlay();
	}

	public void start() {
		getAnimationController().start();
	}

	public void pause() {
		getAnimationController().pause();
	}

	/**
	 * Handles changes to the gravity field
	 * @param event
	 */
	public void applyGravity(KeyEvent event) {
		if(getGravityField().getText().length() > 0) {
			double gravity = Double.valueOf(getGravityField().getText());
			getBallController().getWorld().applyGravity(gravity);
		}
	}
	
	public void fieldChanges(KeyEvent event) {
		TextField textField = (TextField) event.getSource();
		if(textField.getText().length() > 0) {
			validateSpeedFields(getXSpeedField(), getYSpeedField());
		}
	}
}
