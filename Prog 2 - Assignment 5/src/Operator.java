/**
 * Base class for unary operators
 */
public abstract class Operator extends Unary {

	public Operator(Sexpr operand) {
		super(operand);
	}

	@Override
	public String toString() {
		return getName() + operand.toString();
	}
}