import java.util.Map;

public class Abs extends Function {

	public Abs(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "abs";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.abs(operand.eval(map));
	}

}
