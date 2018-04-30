import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.List;

/**
 * Responsible for distributing the result of moving objects
 */
public class MovementHandler {
	private CollisionAction[] mCollisionActions;
	private MoveAction[] mMoveActions;
	private ConsumeAction[] mConsumeActions;
	private CleanupAction[] mCleanupActions;

	private MovementHandler(){ }

	private void setCollisionActions(CollisionAction...collisionActions) {
		mCollisionActions = collisionActions;
	}

	private void setMoveActions(MoveAction...moveActions){
		mMoveActions = moveActions;
	}

	private void setConsumeActions(ConsumeAction...consumeActions) {
		mConsumeActions = consumeActions;
	}

	private void setCleanupActions(CleanupAction...cleanupActions) {
		mCleanupActions = cleanupActions;
	}

	/**
	 * Performs all actions by the nodes specificed by the MovementHandler
	 * @param nodes - nodes to perform actions on
	 */
	public void step(ObservableList<Node> nodes) {
		List<Node> balls = nodes.filtered(node -> ((Role) node).getRole() == Type.BALL);
		List<Node> blackBoxes = nodes.filtered(node -> ((Role) node).getRole() == Type.BLACKBOX);

		blackBoxes.forEach(blackboxNode ->
			balls.forEach(ballNode -> detectInProximity((BlackBox) blackboxNode, (Ball) ballNode))
		);

		balls.forEach(ballNode -> {
			balls.forEach(otherBall -> detectCollision((Ball) ballNode, (Ball) otherBall));
			
			for (MoveAction mMoveAction : mMoveActions) {
				mMoveAction.move((Ball) ballNode);
			}
		});

		for (CleanupAction mCleanupAction : mCleanupActions) {
			mCleanupAction.cleanup();
		}
	}

	/**
	 * Verifies and performs action based on proximity range
	 * @param blackBox - The consumer
	 * @param ball - The consumed
	 */
	private void detectInProximity(BlackBox blackBox, Ball ball) {
		if(blackBox.inProximity(ball)) {
			for (ConsumeAction mConsumeAction : mConsumeActions) {
				mConsumeAction.consume(blackBox, ball);
			}
		}
	}

	/**
	 * Detects collision and performs appropiate action
	 * @param collidee
	 * @param collider
	 */
	private void detectCollision(Ball collidee, Ball collider) {
		if(collidee != collider) {
			if(collidee.collide(collider)) {
				for (CollisionAction mCollisionAction : mCollisionActions) {
					mCollisionAction.collide(collidee, collider);
				}
			}
		}
	}

	/**
	 * Constructs on step frame
	 * @param movement - Movements to perform
	 * @return the actual frame handler
	 */
	public static MovementHandler constructFrame(Movement movement) {
		MovementHandlerBuilder builder = new MovementHandlerBuilder();
		movement.move(builder);
		return builder.evaluate();
	}

	public static class MovementHandlerBuilder {
		private CollisionAction[] nestedCollisionAction;
		private MoveAction[] nestedMoveAction;
		private ConsumeAction[] nestedConsumeAction;
		private CleanupAction[] nestedCleanupAction;

		public MovementHandlerBuilder onBallCollision(CollisionAction...action) {
			nestedCollisionAction = action;
			return this;
		}

		public MovementHandlerBuilder onMove(MoveAction...action) {
			nestedMoveAction = action;
			return this;
		}

		public MovementHandlerBuilder onBlackBoxConsume(ConsumeAction...action) {
			nestedConsumeAction = action;
			return this;
		}

		public MovementHandlerBuilder onCleanup(CleanupAction...cleanupActions) {
			nestedCleanupAction = cleanupActions;
			return this;
		}

		/**
		 * Evaluates and prepares actions performed by and movements
		 * @return
		 */
		private MovementHandler evaluate() {
			MovementHandler handler = new MovementHandler();
			handler.setCollisionActions(nestedCollisionAction);
			handler.setConsumeActions(nestedConsumeAction);
			handler.setMoveActions(nestedMoveAction);
			handler.setCleanupActions(nestedCleanupAction);
			return handler;
		}
	}

	public interface Movement {
		void move(MovementHandler.MovementHandlerBuilder movementBuilder);
	}

	public interface CollisionAction {
		void collide(Ball collide, Ball Collider);
	}

	public interface ConsumeAction {
		void consume(BlackBox blackBox, Ball ball);
	}

	public interface CleanupAction {
		void cleanup();
	}

	public interface MoveAction {
		void move(Ball ball);
	}
}
