import javafx.scene.input.MouseEvent;

/*
 * A controller for node operations within the world
 */

public class NodeController extends WorldController {
	private WorldGenerator mGenerator;

	protected NodeController(World world, AbstractInteraction interaction) {
		super(world);
		mGenerator = new WorldGenerator(world, interaction);
	}
	
	
	/**
	 * Generates a node act from a mouse event
	 * @param act - The actual act, what did you do?
	 * @param event - The event generated by a mouseclick
	 */
	public void generateFrom(FrameAction act, MouseEvent event) {
		mGenerator.actOn(act).with(event);
	}
}
