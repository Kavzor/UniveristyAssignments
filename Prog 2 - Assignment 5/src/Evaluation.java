import java.util.Map;

public class Evaluation extends Operator {

	public Evaluation(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "&";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return operand.eval(map).eval(map);
	}
}
