

import java.util.Random;

import application.geometric.Vector;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
	
	private Vector directionVector;
	private Vector positionVector;
	
	public Ball(int radius, Color color) {
		this.setRadius(radius);
		this.setFill(color);
		Random random = new Random();
		int dx = random.nextInt(11) - 5;
		int dy = random.nextInt(11) - 5;
		directionVector = new Vector(dx, dy);
	}
	
	public Ball(int radius) {
		this(radius, Color.BLACK);
	}
	
	public void setColor(Color color) {
		this.setFill(color);
	}

	public void setStartPos(double x, double y) {
		this.positionVector = new Vector(x, y);
		this.relocate(x, y);
	}
	
	public Vector getDirectionVector() {
		return directionVector;
	}
	
	public void setDirectionVector(Vector vector) {
		this.directionVector = vector;
	}
	
	public Vector getPositionVector() {
		return positionVector;
	}
	
	public void setPositionVector(Vector vector) {
		this.positionVector = vector;
	}
	
	public Bounds getCollisionBounds() {
		return this.getBoundsInLocal();
	}
	
	public boolean collide(Ball ball) {
		
	    if (getCollisionBounds() == null || ball.getCollisionBounds() == null) {
	        return false;
	    }
	 
	    Point2D otherCenter = ball.localToScene(ball.getCenterX(), ball.getCenterY());
	    Point2D thisCenter = this.localToScene(this.getCenterX(), this.getCenterY());
	    double dx = otherCenter.getX() - thisCenter.getX();
	    double dy = otherCenter.getY() - thisCenter.getY();
	    double distance = Math.sqrt(dx * dx + dy * dy);
	    double minDist = ball.getRadius() + this.getRadius();
	 
	    return (distance < minDist);
	}
}
