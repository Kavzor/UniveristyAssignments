/**
 * Represents a constant
 */
import java.util.Map;

public class Constant extends Atom {
	private double value;

	public Constant(double value) {
		this.value = value;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String getName() {
		return "" + value;
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public boolean isConstant(double x) {
		return x==value;
	}

	@Override
	public Sexpr eval(Map<String,Sexpr> map) {
		return this;
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return new Constant(0);
	}
}
