import javafx.event.Event;
import javafx.scene.input.MouseEvent;

public enum FrameAction {
	CLICK_ADD_BALL {
		@Override
		public void apply(Event event, WorldGenerator generator) {
			generator.addBall((MouseEvent) event);

		}
	}, 
	CLICK_REMOVE_BALL {
		@Override
		public void apply(Event event, WorldGenerator generator) {
			generator.removeBall((MouseEvent) event);
		}
	}, 
	CLICK_ADD_BLACKBOX {
		@Override
		public void apply(Event event, WorldGenerator generator) {
			generator.addBlackbox((MouseEvent) event);
		}
	}, 
	CLICK_REMOVE_BLACKBOX {
		@Override
		public void apply(Event event, WorldGenerator generator) {
			generator.removeBlackbox((MouseEvent) event);
		}
	};

	public abstract void apply(Event event, WorldGenerator generator);
}