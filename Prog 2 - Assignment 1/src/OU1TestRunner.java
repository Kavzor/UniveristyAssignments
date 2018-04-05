import org.junit.internal.TextListener;
import org.junit.internal.RealSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class OU1TestRunner {
	
	public static void main(String[] args) {
	    JUnitCore runner = new JUnitCore();
	    TextListener tl = new TextListener(new RealSystem());
	    runner.addListener(tl);
		Result result = runner.run(
				RecursionTest.class,
				StackTest.class
		);
		
		if (result.wasSuccessful()) {
			System.out.println("All " + result.getRunCount() + " tests were successful!");
		} else {
			System.out.println(result.getFailureCount() + " out of " + result.getRunCount() + " tests failed:");
			for(Failure failure : result.getFailures()) {
				System.out.println("Test failed: " + failure);
			}
		}
		
	}
}
