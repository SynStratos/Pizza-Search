package sudoku;

import java.util.*	;

import constraints.Assignment;
import constraints.CSP;
import constraints.Constraint;
import constraints.Variable;

public class SudokuConstraint extends Constraint {
	private List<Variable> variables = new ArrayList<Variable>();
	private List<Object> values = new ArrayList<Object>();
	
	public SudokuConstraint( List<Variable> variables) {
		this.variables = variables;
	}
	
	@Override
	public boolean isSatisfied(Assignment asgn) { //sto passando l'assignment già aggiornato con la nuova entry variabile-valore
		Object temp;
		values.clear();
		//per ogni variabile nell'array variables, cerco quella determinata variabile nella mappa dell'assignment
		for(Variable v : variables) {
			if( asgn.map().containsKey(v) ) {
				temp = asgn.map().get(v);
				if(values.contains(temp)) return false;
				else values.add(temp);
			}
		}
		return true; //il valore non ha riscontrato altre copie nelle variabili già assegnate
	}

	
	public void domainconsistent(CSP csp, Stack<Variable> queue, Variable v){	
		
		for(Variable n : variables) {
			
			//dalla ricerca escludo se stessa
			if(!n.equals(v)) {
				//per ogni vicino prelevo di volta in volta uno dei valori contenuti nel suo dominio
				for(Object o : csp.getDomain(n)) {
					
					//se il dominio della variabile in esame contiene il valore posso continuare con il controllo
					if( csp.getDomain(v).contains(o) ) {
						
						//nel momento in cui il dominio della VARIABILE (privato del valore in esame) dovesse risultare vuoto
						//so che tale valore crea inconsistenza, quindi lo elimino dal dominio del VICINO
						//quest'ultimo, essendo stato modificato, verrà aggiunto alla queue in modo da continuare con i controlli in cascata
						List<Object> temp = new ArrayList<>(csp.getDomain(v));
						temp.remove(o);
				
						if(temp.isEmpty()) {
							//ho condizione di INCONSISTENZA
							//aggiorno il dominio del vicino senza il valore che generava l'inconsistenza
							List<Object> newdomain = new LinkedList<>(csp.getDomain(n));
							newdomain.remove(o);
							
							//in questo caso, in quanto il dominio di almeno un vicino diventa vuoto
							//l'assegnameno è necessariamente inconsistente per il valore assegnato alla variabile
							if(newdomain.size() == 0) {
								csp = null;
								return;
							}else {							
								//aggiorno il dominio del vicino nel csp
								csp.setDomain(n, newdomain);
								//aggiungo il vicino modificato nella coda per il futuro controllo di consistenza
								queue.push(n);
								//inutile controllare altri valori 
								break;
							}
						}
					}
				}
			}		
		}		
	}
	

	@Override
	public List<Variable> variables() {
		return variables;
	}
}