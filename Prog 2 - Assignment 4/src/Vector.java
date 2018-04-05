
/**
 * Represents a vector with non-mutable real x- and y-coordinates. 
 * The class provides vector algebra in a functional way 
 * i. e. the results are returned as new objects and existing objects are never modified.
 */
public class Vector {
	private double x;
	private double y;
	
	/**
	 *  Constructs a Vector object with specified coordinates
	 * @param x - The x-coordinate
	 * @param y - The y-coordinate
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Computes the sum of two vectors.
	 * @param v - The vector to be added to this vector
	 * @return The sum as a new vector
	 */
	public Vector add(Vector v) {
		double newXPos = this.getX() + v.getX();
		double newYPos = this.getY() + v.getY();
		return new Vector(newXPos, newYPos);
	}
	
	/**
	 * Computes the angle of the vector.
	 * @return The angle in radians
	 */
	public double angle() {
		return Math.atan2(this.getY(), this.getX());
	}
	
	/**
	 * Returns the distance from this vector (point) to another vector (point).
	 * @param v - The vector (point) to compute the distance to
	 * @return The distance between this vector and the vector v
	 */
	public double distance(Vector v) {
		double xDistance = v.getX() - this.getX();
		double yDistance = v.getY() - this.getY();
		return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
	}
	
	/**
	 * Computes the scalar product.
	 * @param v - The vector to be multiplied with this vector
	 * @return The scalar product
	 */
	public double dot(Vector v) {
		return (this.getX() * v.getX()) + (this.getY() * v.getY());
	}
	
	/**
	 * Computes a new vector with the same y-coordinate. but with changed sign of the x coordinate. 
	 * This method is good for handling bounce with the left and right walls.
	 * @return
	 */
	public Vector flipSignX() {
		double newXPos = -this.getX();
		double newYPos = this.getY();
		return new Vector(newXPos, newYPos);
	}
	
	/**
	 * Computes a new vector with the same x-coordinate but with changed sign of the y coordinate.
	 * This method is good for handling bounce with the ceiling and the floor.
	 * @return
	 */
	public Vector flipSignY() {
		double newXPos = this.getX();
		double newYPos = -this.getY();
		return new Vector(newXPos, newYPos);
	}
	
	/**
	 * Gets the x-coordinate.
	 * @return The x coordinate
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the y-coordinate.
	 * @return The y coordinate
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Computes the length of the vector
	 * @return The length
	 */
	public double length() {
		return Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));
	}
	
	/**
	 * Creates a vector object using polar coordinates
	 * @param length - The length of the vector to be constructed
	 * @param angle - The angle in radians of the vector to be constructed
	 * @return A new vector object with the specified properties
	 */
	public static Vector polar(double length, double angle) {
		double xPos = length * Math.cos(angle);
		double yPos = length * Math.sin(angle);
		return new Vector(xPos, yPos);
	}
	
	/**
	 * Creates a vector of specified length pointing in a random direction
	 * @param len - the length of the new vector
	 * @return The created vector
	 */
	
	public static Vector randomVector(double len) {
		double angle = Math.random() * 360;
		/*double xPos = len * Math.cos(angle);
		double yPos = len * Math.sin(angle);*/
		return Vector.polar(len, angle);
	}
	
	/**
	 * Computes a new vector pointing in the same direction but with it's length scaled. Example:
     v.scale(2.) will return a vector parallel to but twice as long as the the vector v
	 * @param d
	 * @return A new, scaled vector
	 */
	public Vector scale(double d) {
		double newXPos = this.getX() * d;
		double newYPos = this.getY() * d;
		return new Vector(newXPos, newYPos);
	}
	
	/**
	 * Computes the difference between two vectors.
	 * @param v - The vector to be subtracted from this vector
	 * @return The difference as a new vector
	 */
	public Vector sub(Vector v) {
		double newXPos = this.getX() - v.getX();
		double newYPos = this.getY() - v.getY();
		return new Vector(newXPos, newYPos);
	}
	
	/**
	 * Returns a text representation of the vector
	 * @return The coordinate pair enclosed by < and >
	 */
	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}

}
