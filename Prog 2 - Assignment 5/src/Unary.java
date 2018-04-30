/**
 * Base class for unaries (i.e. functions and unary operators)
 */
public abstract class Unary extends Sexpr {
	protected Sexpr operand;

	public Unary(Sexpr operand) {
		this.operand = operand;
	}

	@Override
	public String toString() {
		return getName() + "(" + operand + ")";
	}
	
	@Override
	public int priority() {
		return 40;
	}
}