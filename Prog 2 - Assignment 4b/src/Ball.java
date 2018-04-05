import java.util.Random;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Representates Ball objects in the frame
 */
public class Ball extends Circle implements Role{
  private final static int RAND_MAX_MIN_SPEED = 10;
  private final static int MAX_MIN_OFFSET_SPEED = 5;

	private Vector directionVector;
	private Vector positionVector;

  /**
   * Should be created through BallBuilder, made private to prevent direct instansiation
   * @param speed
   * @param position
   * @param radius
   * @param color
   */
	private Ball(Vector speed, Vector position, double radius, Color color) {
		directionVector = speed;
		positionVector = position;
		this.setRadius(radius);
		this.setFill(color);
		this.relocate(position.getX(), position.getY());
	}

  /**
   * Generates moving ball speed in any one-dimensional direction
   * @return - A random speed value
   */
	private static int generateRandSpeed() {
	  return new Random().nextInt(RAND_MAX_MIN_SPEED + 1) - MAX_MIN_OFFSET_SPEED;
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

  /**
   * Checkes whatever two spheres are near enough to collide
   * @param ball the collider
   * @return if collider and current ball collides
   */
	public boolean collide(Ball ball) {
		if (getBoundsInLocal() == null || ball.getBoundsInLocal() == null) {
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

  /**
   * Used to create a ball using BallBuilder
   * @param ballMaker - functional interface to make balls
   * @return - The created ball
   */
	public static Ball create(BallMaker ballMaker) {
		BallBuilder builder = new BallBuilder();
		ballMaker.make(builder);
		return builder.build();
	}

	@Override
	public Type getRole() {
		return Type.BALL;
	}

  /**
   * Ball class follows the builder pattern design, thus implementing a inner static BallBuilder class
   */
	public static class BallBuilder {
		private Vector nestedPos;
		private Vector nestedSpeed;
		private Color nestedColor;
		private double nestedRadius;

		public BallBuilder position(double x, double y) {
			nestedPos = new Vector(x, y);
			return this;
		}

    /**
     *
     * @param dx - Defaults to random if set to zero
     * @param dy - Defaults to random if set to zero
     * @return
     */
		public BallBuilder speed(double dx, double dy) {
			if(dx <= 0) {
			  dx = generateRandSpeed();
			}
			if(dy <= 0) {
			  dy = generateRandSpeed();
			}
			nestedSpeed = new Vector(dx, dy);
			return this;
		}
		
		public BallBuilder color(Color color) {
			nestedColor = color;
			return this;
		}
		
		public BallBuilder radius(double radius) {
			nestedRadius = radius;
			return this;
		}

    /**
     * Returns the actual ball
     * @return
     */
		public Ball build() {
			return new Ball(nestedSpeed, nestedPos, nestedRadius, nestedColor);
		}
	}

  /**
   * Used to make balls
   */
  @FunctionalInterface
	public interface BallMaker {
		void make(BallBuilder builder);
	}
}
