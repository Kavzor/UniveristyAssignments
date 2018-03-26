package application;

import application.geometric.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BallAnimation {
	private Timeline mTimeline;
	private Pane mCanvas;
	
	public static final int ANIM_START = 0;
	public static final int ANIM_PAUSE = 1;
	public static final int ANIM_RESUME = 2;
	public static final int ANIM_STOP = 3;
	
	public BallAnimation(Pane canvas) {
		mCanvas = canvas;
		mTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveBalls()));
	}
	
	void start() {
		mTimeline.setCycleCount(Timeline.INDEFINITE);
		mTimeline.play();
	}
	
	public void doAction(int action) {
		switch(action) {
			case ANIM_START:
			case ANIM_RESUME:	mTimeline.play(); break;
			case ANIM_PAUSE:	mTimeline.pause(); break;
			case ANIM_STOP: 	mTimeline.stop(); break;
		}
	}
	
	void moveBalls() {
		mCanvas.getChildren().forEach(this::handleCollision);
		mCanvas.getChildren().forEach(this::moveBall);
	}
	
	void handleCollision(Node currentNode) {
		Ball activeBall = (Ball) currentNode;
		
		for(Node node : mCanvas.getChildren()) {
			Ball collisionBall = (Ball) node;
			if(activeBall != collisionBall) {
				if(activeBall.collide(collisionBall)) {
					activeBall.setColor(Color.RED);
					
					Vector activeOldSpeed = activeBall.getDirectionVector();
					Vector activeOldPos = activeBall.getPositionVector();
					
					Vector collisionOldSpeed = collisionBall.getDirectionVector();
					Vector collisionOldPos = collisionBall.getPositionVector();
					
					
					Vector activeNewSpeed = activeOldSpeed.add(
							collisionOldPos.sub(activeOldPos).
							scale((collisionOldSpeed.sub(activeOldSpeed).
									dot(collisionOldPos.sub(activeOldPos))) / 
									(collisionOldPos.sub(activeOldPos).length() *
									collisionOldPos.sub(activeOldPos).length())));

					activeBall.setDirectionVector(activeNewSpeed);
					
					
//					Vector collisionNewSpeed = collisionOldSpeed.add(
//							collisionOldPos.sub(activeOldPos).
//							scale((collisionOldSpeed.sub(activeOldSpeed).
//									dot(collisionOldPos.sub(activeOldPos))) /
//									(collisionOldPos.sub(activeOldPos).length() *
//									collisionOldPos.sub(activeOldPos).length())));
							
							
//					collisionBall.setDirectionVector(collisionNewSpeed);
				}
			}
		}
	}
	
	
	
	void moveBall(Node node) {
		Ball ball = (Ball) node;
		Vector posVector = ball.getPositionVector();
		Vector dirVector = ball.getDirectionVector();
		double dx = dirVector.getX();
		double dy = dirVector.getY();
		double x = posVector.getX();
		double y = posVector.getY();
		
		x += dx;
		y += dy;
		if(x <= ball.getRadius() || x >= (1000 - ball.getRadius())) {
			x += -dx * 2;
			dirVector = dirVector.flipSignX();
		}
		if(y <= ball.getRadius() || y >= (575 - ball.getRadius())) {
			y += -dy * 2;
			dirVector = dirVector.flipSignY();
		}
		
		ball.setPositionVector(new Vector(x, y));
		ball.setDirectionVector(dirVector);
		
		ball.relocate(x, y);
	}
}