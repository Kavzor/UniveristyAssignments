import java.util.Map;

public class Cos extends Function {

	public Cos(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "cos";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.cos(operand.eval(map));
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.mul(new Constant(-operand.diff(x).getValue()), Symbolic.sin(operand));
	}
}
