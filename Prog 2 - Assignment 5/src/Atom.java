/**
 * Base class for atomic componenent (i.e. constants and variables)
 */
public abstract class Atom extends Sexpr {

	@Override
	public String toString() {
		return getName();
	}
}