

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BallController {
	private enum ClickAction {
		REMOVE_BALL, ADD_BALL, ADD_BLACKBOX, REMOVE_BLACKBOX;
	}
	
	private BallFrame mFrame;
	private BallAnimation mAnimations;
	private ClickAction currentAction = ClickAction.ADD_BALL;
	
	
	public void setBallFrame(BallFrame frame) {
		mFrame = frame;
		mAnimations = frame.getAnimations();
		
		mFrame.addEventFilter(MouseEvent.MOUSE_CLICKED, this::performFrameClickAction);
	}
	
	private void performFrameClickAction(MouseEvent event) {
		switch(currentAction) {
		case ADD_BALL:
				Ball ball = new Ball(10, Color.GREEN);
				ball.setStartPos(event.getX(), event.getY());
				mFrame.addBouncingBall(ball);
			break;
		case ADD_BLACKBOX:
			
			break;
		case REMOVE_BALL:
			break;
		case REMOVE_BLACKBOX:
			break;
		default:
			break;	
		}
	}
	
	public void playAnimations(ActionEvent event) {
		mAnimations.doAction(BallAnimation.ANIM_START);
	}
	
	public void pauseAnimations(ActionEvent event) {
		mAnimations.doAction(BallAnimation.ANIM_PAUSE);
	}
	
	public void resumeAnimations(ActionEvent event) {
		mAnimations.doAction(BallAnimation.ANIM_RESUME);
	}
	
	public void stopAnimations(ActionEvent event) {
		mFrame.clear();
		mAnimations.doAction(BallAnimation.ANIM_STOP);
	}
	
	public void exit(ActionEvent event) {
		System.exit(0);
	}
}
