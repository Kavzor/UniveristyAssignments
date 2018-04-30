/*
 * Controller for all world objects such as animations, acts, model changes to nodes
 */
public abstract class WorldController {
	private World mWorld;
	
	protected WorldController(World world) {
		mWorld = world;
	}
	
	protected World getWorld() {
		return mWorld;
	}
}
