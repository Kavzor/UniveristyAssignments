import java.util.Scanner;

/**
 * Assignment 1 - Recursion algorithms
 * @author Jakob Rolandsson
 *
 *Recursion class contains a several different methods for manipulating data using recursion
 */
public class Recursion {

	public static void main(String...args) {
		double[] ints = {15, 1, 3, 4, 2, 17, 6, 7, 1, 2, 3, 9, 10, 5};
		
		System.out.println("----TESTING POWER----");
		System.out.println("2 powered by 32 is: " + Recursion.power(2, 32) + "\n");
		
		System.out.println("----TESTING MULTIPLY----");
		System.out.println("Product of 2, 2, 5, 6, 1, 2 is: " + Recursion.multiply(2, 2, 5, 6, 1, 2) + "\n");
		
		System.out.println("----TESTING HARMONIC----");
		System.out.println("Harmonic sum of n=10000 is: " + Recursion.harmonic(10000) + "\n");
		
		System.out.println("----TESTING REVERSE WORD----");
		System.out.println("Reverse of \"Hello World\" is: " + Recursion.reverse("Hello World") + "\n");
		
		System.out.println("----TESTING ARRAY MAX VALUE----");
		System.out.println("Array: \"{15, 1, 3, 4, 2, 6, 7, 1, 2, 3, 9, 10, 5, 17}\"");
		System.out.println("Max value of the array is: " + Recursion.max(ints) + "\n");
		
		System.out.println("----TESTING REVERSE NUMBERS----");
		System.out.println("11 23 31 49 56 611 reversed is: " + Recursion.reverseNumbers(new Scanner("11 23 31 49 56 611")) + "\n");
		
		
		int fibIterations = 30;
		System.out.println("----TESTING FIB with " + fibIterations + " iterations----");	
		long startTime = System.currentTimeMillis();
		
		System.out.println("Result: " + Recursion.fib(fibIterations));

		long endTime = System.currentTimeMillis();
		System.out.println("Time: " + ((endTime - startTime) / 1000f) + "s");
	}
	/**
	 * Powers base by exponent
	 * @param base of the expression
	 * @param exp to multiply the base
	 * @return the powered value
	 */
	public static double power(double base, double exp) {
		if(exp == 0) {
			return 1;
		}
		else {
			return power(base, exp - 1) * base;
		}
	}
	
	/**
	 * Multiplies all parameters
	 * @param multipliers to multiply
	 * @return the product of the multipliers
	 */
	public static int multiply(int...multipliers) {
		return multiply(multipliers.length - 1, multipliers);
	}
	
	private static int multiply(int index, int[] multipliers) {
		if(index < 0) {
			return 1;
		}
		else {
			return multipliers[index] * multiply(index - 1, multipliers);
		}
	}
	
	/**
	 * Harmonic sum of n numbers
	 * @param n amount of iterations
	 * @return the harmonic sum of n iterations
	 */
	public static double harmonic(int n) {
		if(n > 0) {
			return (1.0 / n) + harmonic(n - 1);
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Returns max value of non-empty array
	 * @param doubles non-empty array to search for max value
	 * @return defaults Double.MIN_VALUE, else the max value in the array
	 */
	public static double max(double[] doubles) {
		return max(doubles, Double.MIN_VALUE, 0);
	}
	
	private static double max(double[] doubles, double value, int index) {
		if(index > doubles.length - 1) {
			return value;
		}
		else {
			if(doubles[index] > value) {
				value = doubles[index];
			}
			return max(doubles, value, ++index);
		}
	}
	
	/**
	 * Returns string backwards
	 * @param s string to reverse
	 * @return s reveresed
	 */
	public static String reverse(String s) {
		return reverse(s, s.length() - 1);
	}
	
	private static String reverse(String s, int index) {
		if(index < 0) {
			return "";
		}
		else {
			return s.charAt(index) + reverse(s, index - 1);
		}
	}
	
	/**
	 * Receives a scanner of values, value by value rather than char by char
	 * @param scanner with values to reverse
	 * @return the reversed scanner input
	 */
	public static String reverseNumbers(Scanner scanner) {
		if(scanner.hasNext()) {
			String value = scanner.next();
			return reverseNumbers(scanner) + value + " ";
		}
		return "";
	}
	
	
	public static long fib(int n) {
		if(n == 0) {
			return 0;
		}
		else if(n == 1) {
			return 1;
		}
		else {
			return fib(n - 1) + fib(n - 2);
		}
	}
}
