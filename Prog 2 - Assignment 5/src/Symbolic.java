/**
 * Methods for symbolic arithmetic.
 */
public class Symbolic {

	/**
	 * Perform a symbolic/numeric addition
	 * Note: The method should be elaborated to handle
	 * handle several special cases (e.g addition of zero)
	 */
	public static Sexpr add(Sexpr left, Sexpr right) {
		if (left.isConstant() && right.isConstant()) {
			return new Constant(left.getValue() + right.getValue());
		}
		else if(left.isConstant() && left.getValue() == 0d) {
			return right;
		}
		else if(right.isConstant() && right.getValue() == 0d) {
			return left;
		}
		else if(!left.isConstant() && !right.isConstant() && left.equals(right)) {
			return new Multiplication(new Constant(2), right);
		}
		else {
			return new Addition(left, right);
		}
	}

	/**
	 * Perform a symbolic/numeric subtraction
	 * @param left - Left value of expression
	 * @param right - Right value of expression
	 * @return - the result of subtraction
	 */
	public static Sexpr sub(Sexpr left, Sexpr right) {
		if(left.isConstant() && right.isConstant()) {
			return new Constant(left.getValue() - right.getValue());
		}
		else if(left.isConstant() && left.getValue() == 0d) {
			return new Negation(right); //returnera -
		}
		else if(right.isConstant() && right.getValue() == 0d) {
			return left;
		}
		else {
			return new Subtraction(left, right);
		}
	}

	/**
	 * Perform a symbolic/numeric multiplication
	 * Note: The method should be elaborated to handle several
	 * special cases (e.g multiplication with zero or one)
	 */
	public static Sexpr mul(Sexpr left, Sexpr right) {
		if (left.isConstant() && right.isConstant()) {
			return new Constant(left.getValue() * right.getValue());
		}
		else if(left.isConstant() && left.getValue() == 0d) {
			return new Constant(0);
		}
		else if(left.isConstant() && (left.getValue() == 1d || left.getValue() == -1d)) {
			return left.getValue() == 1d ? right : new Negation(right);
		}
		else if(right.isConstant() && right.getValue() == 0d) {
			return new Constant(0);
		}
		else if(right.isConstant() && (right.getValue() == 1d || right.getValue() == -1d)) {
			return right.getValue() == 1d ? left : new Negation(left);
		}
		else {
			return new Multiplication(left, right);
		}
	}

	//if(left.getValue() == 1d) {
	//return right;
	//}
	//else {
	//return new Negation(right);
	//}

	//if(right.getValue() == 1d) {
	//return left;
	//}
	//else {
	//return new Negation(left);
	//}

	/**
	 * Perform a numeric sinus
	 * @return
	 */
	public static Sexpr sin(Sexpr sexpr) {
		return sexpr.isConstant() ? 
				new Constant(Math.sin(sexpr.getValue())) : new Sin(sexpr);
	}

	//if(sexpr.isConstant()) {
	//return new Constant(Math.sin(sexpr.getValue()));
	//}
	//else {
	//return new Sin(sexpr);
	//}

	/**
	 * Perform a numeric cosinus
	 */
	public static Sexpr cos(Sexpr sexpr) {
		return sexpr.isConstant() ?
				new Constant(Math.cos(sexpr.getValue())) : new Cos(sexpr);
	}

	//if(sexpr.isConstant()) {
	//return new Constant(Math.cos(sexpr.getValue()));
	//}
	//else {
	//return new Cos(sexpr);
	//}

	/**
	 * Perform a numeric exp
	 */
	public static Sexpr exp(Sexpr sexpr) {
		return sexpr.isConstant() ? 
				new Constant(Math.exp(sexpr.getValue())) : new Exp(sexpr);
	}

	//if(sexpr.isConstant()) {
	//return new Constant(Math.exp(sexpr.getValue()));
	//}
	//else {
	//return new Exp(sexpr);
	//}

	/**
	 * Perform a numeric log
	 */
	public static Sexpr log(Sexpr sexpr) {
		if(sexpr.isConstant()) {
			if(sexpr.getValue() > 0) {
				return new Constant(Math.log(sexpr.getValue()));
			}
			else {
				throw new EvaluationException("Log cannot be negative");
			}
		}
		else {
			return new Log(sexpr);
		}
	}

	/**
	 * Perform a symbolic/numeric division
	 */
	public static Sexpr div(Sexpr left, Sexpr right) {
		if(right.isConstant() &&
				(right.getValue() == 0d || right.getValue() == 1d || right.getValue() == -1d)) {
			if(right.getValue() == 0d) {
				throw new EvaluationException("Division by zero is undefined"); 
			}
			else {
				return right.getValue() == 1d ? left : new Negation(left);
			}
		}
		else if(left.isConstant() && left.getValue() == 0d) {
			return new Constant(0);
		}
		else if(left.isConstant() && right.isConstant()) {
			return new Constant(left.getValue() / right.getValue());
		}
		else {
			return new Division(left, right);
		}
	}

	//		if(right.isConstant()) {
	//			if(right.getValue() == 0d) {
	//				throw new EvaluationException("Division by zero is undefined");
	//			}
	//		}
	//		if(left.isConstant() && right.isConstant()) {
	//			return new Constant(left.getValue() / right.getValue());
	//		}
	//		else if(left.isConstant() && left.getValue() == 0d) {
	//			return new Constant(0);
	//		}
	//		else if(right.isConstant() && right.getValue() == 1) {
	//			return left;
	//		}
	//		else if(right.isConstant() && right.getValue() == -1d) {
	//			return new Negation(left);
	//		}
	//		else {
	//			return new Division(left, right);
	//		}

	/**
	 * Perform a symbolic/numeric negation
	 */  
	public static Sexpr negate(Sexpr operand) {
		return operand.isConstant() ? 
				new Constant(-operand.getValue()) : new Negation(operand);
	}

	//if (operand.isConstant()) {
	//return new Constant(-operand.getValue());
	//}
	//else {
	//return new Negation(operand);
	//}

	/**
	 * Perofmr a symbolic/numeric abs operation
	 */
	public static Sexpr abs(Sexpr operand) {
		return operand.isConstant() ?
				new Constant(Math.abs(operand.getValue())) : new Abs(operand);
	}

	//if(operand.isConstant()) {
	//return new Constant(Math.abs(operand.getValue()));
	//}
	//else {
	//return new Abs(operand);
	//}
}