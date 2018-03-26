import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * SpecialStack is a class implementing a Deque to get a proper stack behaviour using the most suited collection
 * @author Jakob
 *
 */
public class SpecialStack {
	
	private Deque<Integer> stack;
	
	/*
	 * Empty stack
	 */
	public SpecialStack() {
		this(0);
	}
	
	/**
	 * Stack filled with n numbers, last --> first
	 * @param n to be filled 
	 */
	public SpecialStack(int n) {
		stack = new ArrayDeque<>();
		for(int i = n; i > 0; i--) {
			stack.push(i);
		}
	}
	
	/**
	 * Removes the most recent addition to the stack
	 * @return value removed
	 */
	public int pop() {
		if(stack.isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return stack.pop();
	}
	
	/**
	 * Puts a new addition to the stack on top
	 * @param x value to put on the top
	 */
	public void push(int x) {
		if(!stack.isEmpty() && x > stack.peek()) {
			throw new RuntimeException("Attempting to push a higher value on a low");
		}
		stack.push(x);
	}
	
	/**
	 * Prints the stack as a regular array
	 */
	public String toString() {
		return stack.toString();
	}
}
