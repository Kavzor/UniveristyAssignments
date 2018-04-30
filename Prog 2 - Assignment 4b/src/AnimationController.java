import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * Responsible for handling animations and return useful information related to animations
 */
class AnimationController extends WorldController {
	private static final double MOVEMENT_REFRESH_RATE = 20d;
	private Timeline mBallTimeline;
	private MovementHandler mMovementHandler;
	private boolean isRunning; 

	/**
	 * Instantiates animation view and viewmodel
	 */
	protected AnimationController(World world) {
		super(world);

		mMovementHandler = MovementHandler.constructFrame(builder -> builder.
				onBallCollision(BallAnimation::bounce).
				onMove(BallAnimation::move, world::moveBall).
				onBlackBoxConsume(BallAnimation::consumeByBlackBox, world::destroyBall).
				onCleanup(world::clearGarbage)
			);

		mBallTimeline = new Timeline(new KeyFrame(Duration.millis(MOVEMENT_REFRESH_RATE), this::step));
		mBallTimeline.setCycleCount(Timeline.INDEFINITE);

	}

	protected void start() {
		isRunning = true;
		mBallTimeline.play();
	}

	protected void pause() {
		isRunning = false;
		mBallTimeline.pause();
	}
	protected void togglePlay() {
		if(isRunning) 
			pause();
		else 
			start();
	}

	/**
	 * Called for each frame step performed in the application
	 * @param event - Default event for javafx Keyframes
	 */
	private void step(ActionEvent event) {
		mMovementHandler.step(getWorld().getAllNodes());
	}
}
