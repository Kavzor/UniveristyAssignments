package application;
	
import application.constants.FrameAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Activity extends Context {

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	private ComboBox<String> ballColor;
	
	@FXML
	public void initialize() {
		System.out.println(ballColor);
	}
	
	private FrameAction mCurrentAction = FrameAction.CLICK_ADD_BALL;
	
	@Override
	protected void setupPrimaryListeners(Stage primaryStage) {
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
			if(Keystroke.isWCtrlPressed(event)) {
				super.exit();
			}
		});
		//getBallFrame().addEventFilter(MouseEvent.MOUSE_CLICKED, this::performFrameClickAction);
		getBallFrame().click(this::performFrameClickAction);
		System.out.println(ballColor);
	}
	
	
	public void setClickAddBall() {
		mCurrentAction = FrameAction.CLICK_ADD_BALL;
	}
	
	
	private void performFrameClickAction(MouseEvent event) {
		switch(mCurrentAction) {
		case CLICK_ADD_BALL:
				Ball ball = new Ball(10, Color.GREEN);
				ball.setStartPos(event.getX(), event.getY());
				getBallFrame().addBouncingBall(ball);
			break;
		case CLICK_ADD_BLACKBOX:
			break;
		case CLICK_REMOVE_BALL:
			break;
		case CLICK_REMOVE_BLACKBOX:
			break;
		default:
			break;
		
		}
	}
	
	public void playAnimations(ActionEvent event) {
		getAnimations().doAction(BallAnimation.ANIM_START);
	}
	
	public void pauseAnimations(ActionEvent event) {
		getAnimations().doAction(BallAnimation.ANIM_PAUSE);
	}
	
	public void resumeAnimations(ActionEvent event) {
		getAnimations().doAction(BallAnimation.ANIM_RESUME);
	}
	
	public void stopAnimations(ActionEvent event) {
		getBallFrame().clear();
		getAnimations().doAction(BallAnimation.ANIM_STOP);
	}
	
	public void exit(ActionEvent event) {
		super.exit();
	}
}
