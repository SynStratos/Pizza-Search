package constraints;

import java.util.List;
import java.util.Stack;

public abstract class Constraint {

	//controllo se il constraint è soddisfatto dall'assignment
	public abstract boolean isSatisfied(Assignment asgn);

	//restitutisco le variabili coinvolte nel constraint
	public abstract List<Variable> variables();
	
	//controllo che il dominio sia consistente
	public abstract void domainconsistent(CSP csp, Stack<Variable> queue, Variable v);
}
