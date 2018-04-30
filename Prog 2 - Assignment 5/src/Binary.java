/**
 * Base class for all binary operators
 */
public abstract class Binary extends Sexpr {
	protected Sexpr left;
	protected Sexpr right;

	public Binary(Sexpr left, Sexpr right) {
		this.left = left;
		this.right = right;
	}

	/* 
	 * The toString-method should be refined so that parentheses are used only when needed
	 */
	@Override	
	public String toString() {
		return new StringBuilder().
			append(left.priority() < this.priority() ?
					"(" + left + ")" : left).
			append(getName()).
			append(right.priority() <= this.priority() ?
					"(" + right + ")" : right).
			toString();
	}
}


//StringBuilder builder = new StringBuilder();

/*if(left.priority() < this.priority()) {
builder = builder.append("(" + left + ")");
}
else {
builder = builder.append(left);
}

builder = builder.append(getName());

if(right.priority() <= this.priority()) {
builder = builder.append("(" + right + ")");
}
else {
builder = builder.append(right);
}
return builder.toString();*/
