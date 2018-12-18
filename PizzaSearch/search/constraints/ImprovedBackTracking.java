package constraints;

import java.util.*;

public class ImprovedBackTracking {

	public Assignment solve(CSP csp, Assignment assignment) {

		Assignment result = null;
		
		if( assignment.isComplete(csp.getSize()) ){
			//l'assignment passato è già completo
			result = assignment;
		}else{
			//inizia iterazione di backtracking
			
			//scelgo quale algoritmo di scelta della prima variabile utilizzare (commentato uno o l'altro)
			Variable var = MRV(csp.variables(), assignment);
//			Variable var = DH(csp.variables(), assignment);
			
			/* NB: decommentare il seguente rigo se si vuole shufflare i valori contenuti nel dominio della variabile in esame */
//			Collections.shuffle(vv);
			
			for(Object value : csp.getDomain(var)) {
				assignment.setAssignment(var, value);
				
				if( assignment.isConsistent( var.getConstraints() )){
					result = solve(csp, assignment);
					
					if(result != null) break;
				}
				assignment.delAssignment(var);
			}
		}
		return result;
	}
	

	public Variable MRV(List<Variable> variables, Assignment assignment) {
		Variable MRVvariable = null;
		List<Variable> variable_to_check = new LinkedList<Variable>();
		
		
		int min=100000;
		int num=0;
		int globalMin=100000;
		
		
		for(Variable v : variables) {
			if(!assignment.map().containsKey(v)) {
				Variable t = v;
				for(Constraint c : t.getConstraints()) {
					variable_to_check.addAll(c.variables());
					for(Variable vv : variable_to_check) {
						if(!assignment.map().containsKey(vv)) num++;
					}
					if(num < min) min=num;
					num = 0;
				}
				if (min<globalMin) {
					globalMin = min;
					MRVvariable = v;
				}
			}
		}
		
		return MRVvariable;
	}
	
	public Variable DH(List<Variable> variables, Assignment assignment) {
		Variable DHvariable = null;
		
		int max=0;
		int num=0;
		
		for(Variable v : variables) {
			if(!assignment.map().containsKey(v)) {
				
				num = v.getConstraints().size();
				if(num > max) {
					max = num;
					DHvariable = v;
				}
			}
		}	
		return DHvariable;
	}
	
	
	
	
}
