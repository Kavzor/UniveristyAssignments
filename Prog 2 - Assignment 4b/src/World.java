import javafx.collections.ObservableList;

/**
 * The world object holding all the world objects, e.g. node representations
 */

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class World {

	public final static int MAX_BALL_AMOUNT = 50;

	private List<BlackBox> mBlackBoxes;
	private List<Ball> mBalls;
	private ObservableList<Node> mNodes;

	private double mGravity;

	private List<Ball> mBallsGarbage;
	private List<BlackBox> mBlackBoxesGarbage;

	/**
	 * Constructs a world with lists of boxes, balls and garbage for removals
	 */
	public World(ObservableList<Node> nodes) {
		mNodes = nodes;
		mBlackBoxes = new ArrayList<>();
		mBalls = new LinkedList<>();

		mBallsGarbage = new LinkedList<>();
		mBlackBoxesGarbage = new LinkedList<>();
	}

	public ObservableList<Node> getAllNodes() {
		return mNodes;
	}

	public int getBallsAlive() {
		return mBalls.size();
	}

	private void removeBalls(List<Ball> balls) {
		balls.forEach(ball -> {
			mBalls.remove(ball);
			mNodes.remove(ball);
		});
	}
	
	private void removeBlackBoxes(List<BlackBox> blackBoxes) {
		blackBoxes.forEach(blackBox -> {
			mBlackBoxes.remove(blackBox);
			mNodes.remove(blackBox);
		});
	}

	public void addBall(Ball ball) {
		mBalls.add(ball);
		mNodes.add(ball);
	}

	public void addBlackBox(BlackBox blackBox) {
		mBlackBoxes.add(blackBox);
		mNodes.add(blackBox);
	}

	/**
	 * Clears all world objects both visually and internally
	 */
	public void clear() {
		mNodes.clear();
		mBlackBoxes.clear();
		mBalls.clear();
	}

	public void destroyBall(BlackBox blackBox, Ball ball) {
		mBallsGarbage.add(ball);
	}
	
	public void destroyBlackBox(BlackBox blackBox) {
		mBlackBoxesGarbage.add(blackBox);
	}

	/**
	 * Visually moves the ball in the window according to its current position vector
	 * @param ball - Ball to move
	 */
	public void moveBall(Ball ball) {
		Vector posVector = ball.getPositionVector();
		ball.relocate(posVector.getX(), posVector.getY());
	}

	/**
	 * Removes all the wasted objects on next cycle
	 */
	public void clearGarbage() {
		removeBalls(mBallsGarbage);
		removeBlackBoxes(mBlackBoxesGarbage);
	}

	/**
	 * Applies gravity to the next cycle
	 * @param gravity - Gravity to apply
	 */
	public void applyGravity(double gravity) {
		mGravity = gravity;
	}
}
