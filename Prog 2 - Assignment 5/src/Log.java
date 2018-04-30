import java.util.Map;

public class Log extends Function {

	public Log(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "log";
	}


	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.div(operand.diff(x), operand);
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.log(operand.eval(map));
	}
}
