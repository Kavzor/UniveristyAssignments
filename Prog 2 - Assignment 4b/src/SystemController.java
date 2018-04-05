/**
 * Handles system related calls from user interactions
 */
class SystemController {
  private static final int STATUS_NO_ERR = 0;
  void shutdown() {
		System.exit(STATUS_NO_ERR);
	}
}
