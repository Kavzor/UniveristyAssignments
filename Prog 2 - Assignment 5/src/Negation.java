/**
 * Represents the negation operation ("unary minus")
 */
import java.util.Map;

public class Negation extends Operator {

	public Negation(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "-";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.negate(operand.eval(map));
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.mul(new Constant(-1), operand.diff(x));
	}

	@Override
	public double getValue() {
		return operand.getValue();
	}

	//denna orsakde testDiff_composedArg(CosTest): cos(3*x)'x expected:<[(-3.0*sin(3.0*x)])> but was:<[-3.0*sin(3.0*x])>
	//	@Override
	//	public String toString() {
	//		return "(" + getName() + operand + ")";
	//	}
}