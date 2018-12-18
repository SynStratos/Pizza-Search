package constraints;

import java.util.*;

public class BackTracking {

	
	public Assignment solve(CSP csp, Assignment assignment) {

		Assignment result = null;
		
		if( assignment.isComplete(csp.getSize()) ){
			//l'assignment passato è già completo
			result = assignment;
		}else{
			//inizia iterazione di backtracking
			
			// ricerco la variabile a cui fare l'assignment (in questo caso la prima non assegnata)
			// altri algoritmi per la ricerca della variabile più ottimale sono stati implementati nella stessa libreria
			Variable var = notAssigned(csp.variables(), assignment);
			
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
	
	private Variable notAssigned(List<Variable> vars, Assignment assignment) {
		
		/* NB: decommentare il seguente rigo se si vuole shufflare le variabili del problema prima di procedere con la selezione */
//		Collections.shuffle(vars);
		
		for(Variable v : vars) {
			if(!assignment.map().containsKey(v)) return v;			
		}
		return null;
	}
	
}
