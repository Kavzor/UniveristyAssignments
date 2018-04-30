import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/*
 * Responsible for generating events based on acts
 */
public class WorldGenerator {
	

	private World mWorld;
	private FrameAction mCurrentAct;
	private AbstractInteraction mInteraction;

	public WorldGenerator(World world, AbstractInteraction interaction) {
		mWorld = world;
		mInteraction = interaction;
	}

	public WorldGenerator actOn(FrameAction act) {
		mCurrentAct = act;
		return this;
	}

	public void with(MouseEvent event) {
		mCurrentAct.apply(event, this);
	}

	/**
	 * Spawns a new ball at clicked location
	 * @param event - Event that triggered the generation
	 */
	protected void addBall(MouseEvent event) {
		if(mWorld.getBallsAlive() < World.MAX_BALL_AMOUNT) {
			Color color = mInteraction.getColorPicker().getValue();
			double dx = Double.valueOf(mInteraction.getXSpeedField().getText());
			double dy = Double.valueOf(mInteraction.getYSpeedField().getText());
			double radius = Double.valueOf(mInteraction.getRadiusField().getText());
	
			mWorld.addBall(Ball.create(builder -> builder.
					position(event.getX(), event.getY()).
					speed(dx, dy).
					radius(radius).
					color(color)
				));
		}
	}

	/**
	 * Handles spawning of blackboxes at predefined coordinates
	 * @param event - Event that triggered the generation
	 */
	protected void addBlackbox(MouseEvent event) {
		mWorld.addBlackBox(new BlackBox(event.getX(), event.getY()));
	}

	/**
	 * Potentially removes a ball node if clicked successfully
	 * @param event
	 */
	protected void removeBall(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		if(node instanceof Ball) {
			mWorld.destroyBall(null, (Ball) node);
		}
	}

	/**
	 * Potentially removes a blackBox if clicked successfully
	 * @param event
	 */
	protected void removeBlackbox(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		if(node instanceof BlackBox) {
			mWorld.destroyBlackBox((BlackBox) node);
		}
	}
}
