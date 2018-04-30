import java.util.Map;

public class Exp extends Function {

	public Exp(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "exp";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.exp(operand.eval(map));
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.mul(operand.diff(x), new Exp(operand));
	}
}
