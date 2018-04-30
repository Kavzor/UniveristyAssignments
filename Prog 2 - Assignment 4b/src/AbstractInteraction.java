import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Manages all the non-interactive actions and UI tools
 */
public abstract class AbstractInteraction {
	private WorldController mAnimationController;
	private WorldController mBallController;
	private SystemController mSystemController;

	private PerformanceTracker mPerformanceTracker;
	private FrameAction mCurrentAction = FrameAction.CLICK_ADD_BALL;


	private static final String INVALID_FIELD = "-fx-control-inner-background: #ff3333";
	private static final String VALID_FIELD = "-fx-control-inner-background: #55ff00";
	private static final double LABELS_REFRESH_RATE = 100d;

	@FXML
	private Pane mBallFrame;

	@FXML
	private TextField mXSpeed;

	@FXML
	private TextField mYSpeed;

	@FXML
	private TextField mRadius;

	@FXML
	private ColorPicker mColor;

	@FXML
	private TextField mGravity;

	@FXML
	private Text mBallsAlive;

	@FXML
	private Text mFPS;

	@FXML
	public void initialize() {
		mSystemController = new SystemController();
		World world = new World(mBallFrame.getChildren());
		mAnimationController = new AnimationController(world);
		mBallController = new NodeController(world, this);
	}

	/**
	 * Instansiates controllers used in the interaction and boots refreshing of ui labels
	 */
	public AbstractInteraction() {
		refreshUILabels();
	}
	
	private void refreshUILabels() {
		Timeline uiLabelsLine = new Timeline(new KeyFrame(Duration.millis(LABELS_REFRESH_RATE), event -> {
			refreshFPS();
			refreshBallsAlive();
		}));
		uiLabelsLine.setCycleCount(Timeline.INDEFINITE);
		uiLabelsLine.play();
	}


	protected AnimationController getAnimationController() {
		return (AnimationController) mAnimationController;
	}

	protected NodeController getBallController() {
		return (NodeController) mBallController;
	}

	protected SystemController getSystemController() {
		return mSystemController;
	}

	protected TextField getXSpeedField() {
		return mXSpeed;
	}

	protected TextField getYSpeedField() {
		return mYSpeed;
	}

	protected TextField getRadiusField() {
		return mRadius;
	}

	protected ColorPicker getColorPicker() {
		return mColor;
	}

	protected TextField getGravityField() {
		return mGravity;
	}

	protected Text getBallsAliveLabel() {
		return mBallsAlive;
	}

	protected Text getFPSLabel() {
		return mFPS;
	}

	/**
	 * fetches the performance handler for the current scene
	 * @return the performance handler if possible else it passes null
	 */
	protected PerformanceTracker getPerformanceTracker() {
		mPerformanceTracker = mPerformanceTracker == null ?
				PerformanceTracker.getSceneTracker(mBallFrame.getScene()) : mPerformanceTracker;
		return mPerformanceTracker;
	}

	protected void setFrameAction(FrameAction action) {
		mCurrentAction = action;
	}
	
	protected FrameAction getFrameAction() {
		return mCurrentAction;
	}

	protected void validateSpeedFields(TextField...speedFields) {
		for(TextField speedField : speedFields) {
			if(isValidSpeedInput(speedField)) {
				speedField.setStyle(VALID_FIELD);
			}
			else {
				speedField.setStyle(INVALID_FIELD);
			}
		}
	}

	private boolean isValidSpeedInput(TextField speedField) {
		try {
			double speed = Double.valueOf(speedField.getText());
			return speed <= Ball.MAX_SPEED;
		}
		catch(NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * Refreshes the FPS label
	 */
	protected void refreshFPS() {
		double fps = Math.round(getPerformanceTracker().getInstantFPS());
		getFPSLabel().setText("FPS: " + fps);
	}

	/**
	 * Refreshes the balls alive label
	 */
	protected void refreshBallsAlive() {
		int ballsAlive = getBallController().getWorld().getBallsAlive();

		Color ballsAliveColor = ballsAlive < World.MAX_BALL_AMOUNT ? Color.GREEN : Color.RED;
		getBallsAliveLabel().setFill(ballsAliveColor);
		getBallsAliveLabel().setText("Balls alive: " + ballsAlive);
	}
}
