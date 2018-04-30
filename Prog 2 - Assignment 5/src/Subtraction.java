import java.util.Map;

public class Subtraction extends Binary {

	public Subtraction(Sexpr left, Sexpr right) {
		super(left, right);
	}

	@Override
	public String getName() {
		return "-";
	}

	@Override	
	public int priority() {
		return 20;
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return Symbolic.sub(left.eval(map), right.eval(map));
	}

	
	@Override
	public Sexpr diff(Sexpr x) {
		return Symbolic.sub(left.diff(x), right.diff(x));
	}

}
