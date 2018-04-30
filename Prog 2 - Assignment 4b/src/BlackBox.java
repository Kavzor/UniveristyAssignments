import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Representates the blackboxes (deathboxes) in the frame
 */
public class BlackBox extends Rectangle implements Role {
  private static final double BLACKBOX_SIDE = 75.0d;

  private Vector mPosition;

  /**
   * Creates a blackbox of BLACKBOX_SIDE side-length at x, y
   * @param x - The x coordinate
   * @param y - The y coordinate
   */
  public BlackBox(double x, double y) {
    mPosition = new Vector(x, y);
    this.setWidth(BLACKBOX_SIDE);
    this.setHeight(BLACKBOX_SIDE);
    this.relocate(x, y);
    this.setFill(Color.BLACK);
  }

  @Override
  public Type getRole() {
    return Type.BLACKBOX;
  }

  /**
   * Retrieves whatever a sphere (ball) is near (touching) a blackbox
   * @param ball - The ball that is being checked
   * @return - If the ball touches the blackbox
   */
  public boolean inProximity(Ball ball) {
    return ball.getBoundsInParent().intersects(this.getBoundsInParent());
  }
}
