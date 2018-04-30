/**
 * Represents a variable
 */
import java.util.Map;

public class Variable extends Atom {
	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Sexpr eval(Map<String,Sexpr> map) {
		if (map.containsKey(name)) {
			return map.get(name);
		} else {
			throw new EvaluationException("Undefined varible: " + name);
		}
	}

	@Override
	public Sexpr diff(Sexpr x) {
		return x.getName().equals(name) ?
				new Constant(1) : new Constant(0);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj ?
				true : obj.toString().equals(this.toString());
	}
}

/*
if(x.getName().equals(name)) {
	return new Constant(1);
}
else {
	return new Constant(0);		
}*/

/*if(this == obj) {
return true;
}
else {
return obj.toString().equals(this.toString());
}*/
