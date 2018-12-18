package constraints;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class AC3 {
	
	public Assignment solve(CSP csp, Assignment assignment) {
		
		Assignment result = null;
		//salvo lo stato precedente del dominio della variabile in modo da ripristinarlo se l'assegnazione dovesse risultare inconsistente
		List<Object> olddomain = null;
		
		// coda delle variabili di cui controllare la consistenza con i vicini
		// di volta in volta in cui rendo un dominio consistente, aggiungerò la variabile coinvolta nella modifica all'interno della coda
		Stack<Variable> queue = new Stack<Variable>();
		
		if( assignment.isComplete(csp.getSize())) {
			//se l'assignment risulta completo vuol dire che ho già la soluzione
			System.out.println("final assignment found");
			result = assignment;
		}else{
			//inizio iterazione
			//sceglo la variabile da provare ad assegnare
			//possibile utilizzare altri algoritmi già implementati nella libreria
			Variable v = notAssigned(csp.variables(), assignment);
		
			//se il dominio della variabile in esame è già nullo vuol dire che non ho una soluzione al problema
			//ritorno quindi result = null per procedere con una prova differente
			//TODO: dovrebbe essere superfluo nel momento in cui da *FORWARD CHECKING* passo ad *AC3*
			if ( csp.getDomain(v).size() == 0) return result;
			
			//salvo il dominio della variabile in esame in modo da ripristinarlo nel caso in cui l'assignment non sia valido
			
			//devo memorizzare dominio precedente con variabile
			olddomain = new ArrayList<>(csp.getDomain(v));
			
			for(Object val : csp.getDomain(v)) {
				assignment.setAssignment(v, val);				
				
				//creo dominio aggiornato per la variabile con il valore assegnato e lo aggiorno	
				List<Object> list = new LinkedList<>();
				list.add(val);
				csp.setDomain(v, list);
				
				//aggiungo alla coda delle variabili 
				queue.push(v);
				CSP tempcsp = csp;
				
				while(!queue.isEmpty()) {
					v= queue.pop();
					for(Constraint c : v.getConstraints()) {
						c.domainconsistent(tempcsp, queue, v);
						if(tempcsp == null) break;
					}
					if(tempcsp == null) break;
				}
				

				//se non è inconsistente
				if(tempcsp != null) {
					result = solve(tempcsp, assignment);
					if (result != null) break;
				}

				csp.setDomain(v, olddomain);
				assignment.delAssignment(v);
			}			
		}
		return result;		
	}
	
	private Variable notAssigned(List<Variable> vars, Assignment assignment) {
		
		//Collections.shuffle(vars);
		
		for(Variable v : vars) {
			if(!assignment.map().containsKey(v)) return v;			
		}
		return null;
	}
	
}
