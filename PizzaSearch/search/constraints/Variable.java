package constraints;

import java.util.*;

public class Variable {
	
	//id da assegnare
	private Object value;

	private List<Constraint> constraints = new LinkedList<Constraint>();
	
	public Variable(Object value) {
		this.value = value;
	}
	
	public Object value() {
		return value;
	}
	//funzioni per constraints da aggiungere
	public void addConstraints(List<Constraint> constraint) {
		constraints.addAll(constraint);
	}
	
	public void addConstraint(Constraint constraint) {
		constraints.add(constraint);
	}
	
	public List<Constraint> getConstraints(){
		return constraints;
	}

	public List<Variable> neighbours(){
		List<Variable> neigh = new LinkedList<Variable>();
		//per rimuovere i duplicati
		Set<Variable> set = new HashSet<Variable>();
		for(Constraint c : constraints) {
			set.addAll(c.variables());
		}
		//rimuovo la variabile in questione dall'elenco dei vicini
		set.remove(this);
		neigh.addAll(set);
		return neigh;
	}
	
}
