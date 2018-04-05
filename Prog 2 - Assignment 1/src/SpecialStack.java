
import java.util.*;

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
		if(stack.isEmpty()) {
			return "[]";
		} else {
			List<Integer> ints = new ArrayList<>();
			ints.addAll(stack);
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = ints.size() - 1; i > -1; i--) {
				sb.append(ints.get(i) + ", ");
			}
			sb.append("]");
			return sb.toString().replaceAll(", ]", "]");
		}
	}
}
