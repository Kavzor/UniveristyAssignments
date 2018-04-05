import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Handles all the interaction of the GUI and distributes them to the respective controller
 */
public class Interaction extends AbstractInteraction {
	private FrameAction frameAction = FrameAction.CLICK_ADD_BALL;
  private static final double LABELS_REFRESH_RATE = 100d;

  /**
   * Handles any click events invoked within the ball frame
   * @param event - The event performed
   */
	public void handleFrameClick(MouseEvent event) {
		if(!(event.getTarget() instanceof Circle)) {
			switch(frameAction) {
			case CLICK_ADD_BALL:
			  if(getWorld().getBallsAlive() < 50){
          addBall(event.getX(), event.getY());
        }
				break;
			case CLICK_ADD_BLACKBOX:
				addBlackbox(event.getX(), event.getY());
				break;
			case CLICK_REMOVE_BALL:
				break;
			case CLICK_REMOVE_BLACKBOX:
				break;
			default:
				break;
			}
		}
	}

  /**
   * Spawns a new ball at clicked location
   * @param x - Ball's x coordinate
   * @param y - Ball's y coordinate
   */
	private void addBall(double x, double y) {
		double dx = Double.valueOf(getXSpeedField().getText());
		double dy = Double.valueOf(getYSpeedField().getText());
		double radius = Double.valueOf(getRadiusField().getText());
    getWorld().addBall(Ball.create(builder -> builder.
            position(x, y).
            speed(dx, dy).
            radius(radius).
            color(getColorPicker().getValue()).
            build()
    ));
	}

  /**
   * Handles spawning of blackboxes at predefined coordinates
   * @param x - Blackbox's x coordinate
   * @param y - Blackbox's y coordinate
   */
	private void addBlackbox(double x, double y) {
    getWorld().addBlackBox(new BlackBox(x, y));
	}
	
	private void removeBall() {

	}
	
	private void removeBlackbox() {
		
	}

	/** file **/
	public void setClickAddBall() {
		this.frameAction = FrameAction.CLICK_ADD_BALL;
	}
	
	public void setClickRemoveBall() {
		this.frameAction = FrameAction.CLICK_REMOVE_BALL;
	}
	
	public void setClickAddBlackBox() {
		this.frameAction = FrameAction.CLICK_ADD_BLACKBOX;
	}
	
	public void setClickRemoveBlackBox() {
		this.frameAction = FrameAction.CLICK_REMOVE_BLACKBOX;
	}
	
	public void shutdown() {
		getSystemController().shutdown();
	}
	
	public void clearField() {
		getWorld().clear();
	}
	
	/** animation **/
	
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
			getWorld().applyGravity(gravity);
		}
	}

	public void refreshUILabels() {
	  Timeline uiLabelsLine = new Timeline(new KeyFrame(Duration.millis(LABELS_REFRESH_RATE), this::refreshLabels));
	  uiLabelsLine.setCycleCount(Timeline.INDEFINITE);
	  uiLabelsLine.play();
	}

  /**
   * Internal method for refreshing the labels every LABELS_REFRESH_RATE milisecond
   * @param event
   */
  private void refreshLabels(ActionEvent event) {
    refreshFPS();
    refreshBallsAlive();
  }

  /**
   * Refreshes the FPS label
   */
  private void refreshFPS() {
	  double fps = Math.round(getPerformanceTracker().getInstantFPS());
    getFPSLabel().setText("FPS: " + fps);
  }

  /**
   * Refreshes the balls alive label
   */
  private void refreshBallsAlive() {
    int ballsAlive = getWorld().getBallsAlive();

    Color ballsAliveColor = ballsAlive < 50 ? Color.GREEN : Color.RED;
    getBallsAliveLabel().setFill(ballsAliveColor);
    getBallsAliveLabel().setText("Balls alive: " + ballsAlive);
  }
}
