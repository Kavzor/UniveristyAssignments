import com.sun.javafx.perf.PerformanceTracker;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Manages all the non-interactive actions and UI tools
 */
public abstract class AbstractInteraction {
	enum FrameAction {
		CLICK_ADD_BALL, CLICK_REMOVE_BALL, CLICK_ADD_BLACKBOX, CLICK_REMOVE_BLACKBOX;
	}
	
	private AnimationController mAnimationController;
	private SystemController mSystemController;

	private PerformanceTracker mPerformanceTracker;
	
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
		getWorld().setWorldObjects(mBallFrame.getChildren());
	}

  /**
   * Instansiates controllers used in the interaction and boots refreshing of ui labels
   */
	public AbstractInteraction() {
		mAnimationController = new AnimationController();
		mSystemController = new SystemController();
		refreshUILabels();
	}

	protected abstract void refreshUILabels();

	World getWorld() {
	  return mAnimationController.getWorld();
  }

	AnimationController getAnimationController() {
		return mAnimationController;
	}
	
	SystemController getSystemController() {
		return mSystemController;
	}

	TextField getXSpeedField() {
		return mXSpeed;
	}
	
	TextField getYSpeedField() {
		return mYSpeed;
	}
	
	TextField getRadiusField() {
		return mRadius;
	}
	
	ColorPicker getColorPicker() {
		return mColor;
	}
	
	TextField getGravityField() {
		return mGravity;
	}

	Text getBallsAliveLabel() {
		return mBallsAlive;
	}

	Text getFPSLabel() {
	  return mFPS;
  }

  /**
   * fetches the performance handler for the current scene
   * @return the performance handler if possible else it passes null
   */
  PerformanceTracker getPerformanceTracker() {
    mPerformanceTracker = mPerformanceTracker == null ?
            PerformanceTracker.getSceneTracker(mBallFrame.getScene()) : mPerformanceTracker;
    return mPerformanceTracker;
  }
}
