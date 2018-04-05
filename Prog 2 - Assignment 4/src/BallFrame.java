

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class BallFrame extends Pane {
	
	private BallAnimation mBallAnimation;
	
	public BallFrame() {
		mBallAnimation = new BallAnimation(this);
		mBallAnimation.start();
	}
	
	public void addBouncingBall(Ball ball) {
		getChildren().add(ball);
	}
	
	public void addDeathBox() {
		
	}
	
	public void clear() {
		getChildren().clear();
	}
	
	public BallAnimation getAnimations() {
		return mBallAnimation;
	}
	
	public void click(EventHandler<MouseEvent> eventHandler) {
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
}
