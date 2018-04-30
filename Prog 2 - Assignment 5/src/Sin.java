import java.util.Map;

public class Sin extends Function {

	public Sin(Sexpr operand) {
		super(operand);
	}
	
	@Override
	public String getName() {
		return "sin";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.sin(operand.eval(map));
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.mul(operand.diff(x), Symbolic.cos(operand));
	}
}
