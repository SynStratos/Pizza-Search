package constraints;

import java.util.*;

public class Assignment {
	
//	List<Variable> variables;
	Map<Variable, Object> assigned;
	
	
	public Assignment() {
//		variables = new ArrayList<Variable>();
		assigned = new HashMap<Variable, Object>();		   
	}
	
	public void setAssignment(Variable v, Object val) {
		if(!assigned.containsKey(v)) assigned.put(v, val);
	}
	
	public void delAssignment(Variable v) {
		if(assigned.containsKey(v)) assigned.remove(v);
	}	
	
	public Map<Variable, Object> map(){
		return assigned;
	}
	
	//durante l'esecuzione del backtracking
	//	scelgo variabile e assegno valore casuale nel dominio
	//	aggiungo la variabile nell'assignment
	//	passo l'elenco di constraint relativi
	
	public boolean isConsistent( List<Constraint> constraints) {
		for( Constraint c : constraints)
			if (!c.isSatisfied(this)) return false;		
		return true;
	}
	
	public boolean isComplete(int n) {
		
		if(n == assigned.size()) return true;
		
		return false;
	}
	

}
