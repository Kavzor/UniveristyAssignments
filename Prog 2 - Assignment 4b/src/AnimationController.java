import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * Responsible for handling animations and return useful information related to animations
 */
class AnimationController {
  private static final double MOVEMENT_REFRESH_RATE = 20d;
	private Timeline mBallTimeline;
	private BallAnimation mBallAnimation;

	private World mWorld;

	private MovementHandler mMovementHandler;

  /**
   * Instansiates animation view and viewmodel
   */
	protected AnimationController() {
		mBallAnimation = new BallAnimation();
		mWorld = new World();

    mMovementHandler = MovementHandler.constructFrame(builder -> builder.
            onBallCollision(mBallAnimation::bounce).
            onMove(mBallAnimation::move, mWorld::moveBall).
            onBlackBoxConsume(mBallAnimation::consumeByBlackBox, mWorld::destroyBall).
            onCleanup(mWorld::clearGarbage)
    );

		mBallTimeline = new Timeline(new KeyFrame(Duration.millis(MOVEMENT_REFRESH_RATE), this::step));
		mBallTimeline.setCycleCount(Timeline.INDEFINITE);

	}

  World getWorld() {
    return mWorld;
  }

	void start() {
		mBallTimeline.play();
	}

	void pause() {
		mBallTimeline.pause();
	}

  /**
   * Called for each frame step performed in the application
   * @param event - Default event for javafx Keyframes
   */
	private void step(ActionEvent event) {
	  mMovementHandler.step(mWorld.getAllNodes());
  }
}
