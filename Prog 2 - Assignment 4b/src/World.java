import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class World {

  private List<BlackBox> mBlackBoxes;
  private List<Ball> mBalls;
  private ObservableList<Node> mNodes;

  private double mGravity;

  private List<Ball> mBallsGarbage;

  public World() {
    mBlackBoxes = new ArrayList<>();
    mBalls = new LinkedList<>();

    mBallsGarbage = new LinkedList<>();
  }

  public void setWorldObjects(ObservableList<Node> nodes) {
    mNodes = nodes;
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

  public void addBall(Ball ball) {
    mBalls.add(ball);
    mNodes.add(ball);
  }

  public void addBlackBox(BlackBox blackBox) {
    mBlackBoxes.add(blackBox);
    mNodes.add(blackBox);
  }

  public void clear() {
    mNodes.clear();
    mBlackBoxes.clear();
    mBalls.clear();
  }

  public void destroyBall(BlackBox blackBox, Ball ball) {
    mBallsGarbage.add(ball);
  }

  public void moveBall(Ball ball) {
    Vector posVector = ball.getPositionVector();
    ball.relocate(posVector.getX(), posVector.getY());
  }

  public void clearGarbage() {
    removeBalls(mBallsGarbage);
  }

  public void applyGravity(double gravity) {
    mGravity = gravity;
  }
}
