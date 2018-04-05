import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BlackBox extends Rectangle {
	
	private Vector mPosition;
	
	public BlackBox(double x, double y) {
		mPosition = new Vector(x, y);
		this.relocate(x, y);
	}
	
	public boolean collide(Ball ball) {
		double xPos = mPosition.getX();
		double yPos = mPosition.getY();
		double ballXPos = ball.getCenterX();
		double ballYPos = ball.getCenterY();
		
		double distanceX = Math.abs(xPos - ballXPos);
		double distanceY = Math.abs(yPos - ballYPos);
		
		
		return false;
	}
}
