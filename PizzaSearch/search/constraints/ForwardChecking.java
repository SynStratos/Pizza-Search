package constraints;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ForwardChecking {

	public Assignment solve(CSP csp, Assignment assignment) {
		
		
		
		Assignment result = null;
		Map<Variable, List<Object>> backup = new HashMap<>();
		
		if( assignment.isComplete(csp.getSize())) {
			//se l'assignment risulta completo vuol dire che ho già la soluzione
			result = assignment;
		}else{
			//sceglo la variabile da provare ad assegnare
			//possibile utilizzare altri algoritmi già implementati nella libreria
			Variable v = notAssigned(csp.variables(), assignment);
		
			//se il dominio della variabile in esame è già nullo vuol dire che non ho una soluzione al problema
			//ritorno quindi result = null per procedere con una prova differente
			if ( csp.getDomain(v).size() == 0) return result;
			
			
			//NB: NON GENERICO -> FUNZIONA SOLAMENTE PER ALGORITMI CON CONSTRAINTS "ALL DIFFERENT" -> FUTURA IMPLEMENTAZIONE GENERICA
			for(Object val : csp.getDomain(v) ) {
				assignment.setAssignment(v, val);
				
				//devo memorizzare dominio precedente con variabile
				backup.put(v, csp.getDomain(v));
				
				//creo dominio aggiornato per la variabile con il valore assegnato e lo aggiorno	
				List<Object> newdomain = new LinkedList<>(Arrays.asList(val));
				
				csp.setDomain(v, newdomain);
				
				for(Variable n : v.neighbours()) {
					
					//aggiungo il vecchio dominio delle variabili vicine nel backup
					backup.put(n, csp.getDomain(n));
					//aggiorno il dominio delle variabili vicine rimuovendo il valore appena assegnato alla variabile in esame
					newdomain = new LinkedList<>(csp.getDomain(n));
					newdomain.remove(val);				
					
					//aggiorno lo stato del problema con i nuovi domini
					csp.setDomain(n, newdomain);
				}
			
				result = solve(csp, assignment);
				if (result != null) break;
				//se il risultato non è corretto ripristino i valori dei domini salvati nella mappa Backup 
				//in questo modo torno allo stato precedente
				
				for( Variable key : backup.keySet() ) {
					csp.setDomain(key, backup.get(key ));
				}
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
