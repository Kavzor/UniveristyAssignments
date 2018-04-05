import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * Handles any vector related movement
 */
public class BallAnimation {
  /**
   * Handles collision between two spheres
   * @param currentNode - Collidee
   * @param collisionNode - Collider
   */
	public void bounce(Node currentNode, Node collisionNode) {
		Ball activeBall = (Ball) currentNode;
		Ball collisionBall = (Ball) collisionNode;
		//activeBall.setRadius(activeBall.getRadius() + 0.1);

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

		Vector collisionNewSpeed = collisionOldSpeed.add(
				activeOldPos.sub(collisionOldPos).
				scale((activeOldSpeed.sub(collisionOldSpeed).
						dot(activeOldPos.sub(collisionOldPos))) / 
						(activeOldPos.sub(collisionOldPos).length() *
								activeOldPos.sub(collisionOldPos).length())));
		

		collisionBall.setPositionVector(collisionOldPos.sub(collisionOldSpeed));
		collisionBall.setDirectionVector(collisionNewSpeed);
		
		activeBall.setPositionVector(activeOldPos.sub(activeOldSpeed));
		activeBall.setDirectionVector(activeNewSpeed);
	}

  /**
   * Moves a world object (node) according to its' current position and speed
   * @param node - Node to be moved
   */
	public void move(Node node) {
		Ball ball = (Ball) node;
		Bounds parentBoundaries = node.getParent().getLayoutBounds();
		double parentWidth = parentBoundaries.getWidth();
		double parentHeight = parentBoundaries.getHeight();

		Vector posVector = ball.getPositionVector();
		Vector dirVector = ball.getDirectionVector();

		posVector = posVector.add(dirVector);

		double x = posVector.getX();
		double y = posVector.getY();

		if(x <= ball.getRadius()) {
		  x = ball.getRadius() * 2;
      dirVector = dirVector.flipSignX();
		}
    else if(x >= (parentWidth - ball.getRadius())) {
      x = parentWidth - (ball.getRadius() * 2);
      dirVector = dirVector.flipSignX();
    }

		if(y <= ball.getRadius()) {
		  y = ball.getRadius() * 2;
		  dirVector = dirVector.flipSignY();
    }
    else if(y >= (parentHeight - ball.getRadius())) {
      y = parentHeight - (ball.getRadius() * 2);
      dirVector = dirVector.flipSignY();
    }
		

		ball.setPositionVector(new Vector(x, y));
		ball.setDirectionVector(dirVector);
	}

  /**
   * Used to animate or pre-movements when about to be consumed by a blackbox
   * @param blackBox - Consumer
   * @param ball - Consumed
   */
	public void consumeByBlackBox(BlackBox blackBox, Ball ball) {

  }
}