/**
 * Represents the quotation operation
 */
import java.util.Map;

public class Quotation extends Operator {

	public Quotation(Sexpr operand) {
		super(operand);
	}

	@Override
	public String getName() {
		return "\"";
	}

	@Override
	public Sexpr eval(Map<String, Sexpr> map) {
		return operand;
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return operand.diff(x);
	}
}