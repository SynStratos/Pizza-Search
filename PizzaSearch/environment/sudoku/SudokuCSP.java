package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import constraints.*;

public class SudokuCSP extends CSP{

	private List<Variable> variables; //contengono dominio della variabile e constraints
	private Assignment assignment; //dove salvo i valori assegnati di volta in volta (stato)
	private Map<Variable, List<Object>> domains;
	private int dim;
	
	public SudokuCSP(List<Object> domain, String input, int dim) {
		variables = new ArrayList<Variable>();
		assignment = new Assignment();
		domains = new HashMap<Variable, List<Object>>();
		this.dim = dim;
		
		List<Object> varDomain = new LinkedList<Object>();
		varDomain.addAll(domain);
		//creazione delle variabili con constraints e dominio
		for(int i=0; i<dim * dim; i++) {
			
			Variable temp = new Variable(i);
			variables.add(temp);
			domains.put(temp, varDomain);
			//se la variabile è stata assegnata nella stringa iniziale salvo già il valore
			//all'interno della mappa degli assignment
			int val = Integer.parseInt(input.substring(i, i+1));
			if( val != 0) { 
				assignment.setAssignment(temp, val);
			}			
		}
		
		// N.B. non posso farlo già nel ciclo precedente in quanto non ho l'elenco completo delle variabili
		for(Variable v : variables) {
			v.addConstraints( varConstraints(v) );
		}
		
	}
	
	//funzione per calcolare i constraint di ogni variabile
	//rispettivamente saranno relativi alle variabili della stessa riga, della stessa colonna e dello stesso riquadro di quella passata come parametro
	private List<Constraint> varConstraints(Variable v){
		
		List<Constraint> list = new LinkedList<Constraint>();
		List<Variable> temporary = new LinkedList<Variable>();
		
		int index = variables.indexOf(v);
		int sqrt = (int)Math.sqrt(dim);
		
		
		//constraint per il rigo
		int it = index / dim;
		it = it*dim;
		int cap = it + dim;
		for(int k = it; k<cap; k++) {
			temporary.add( variables.get(k) );
		}
		list.add( new SudokuConstraint(temporary));
		
		//constraint per la colonna
		temporary = new LinkedList<Variable>();
		
		int q = index%dim;
		for(int kit=q; kit < dim*dim; kit +=dim) {
			temporary.add( variables.get(kit));
		}
		list.add( new SudokuConstraint(temporary) );
		
		//contraint per il riquadro
		temporary= new LinkedList<Variable>();
		
		int a = index / (dim*sqrt);
		int b = index % (dim*sqrt);
		
		b = b % dim;
		
		b = b / sqrt;
		b = b * sqrt + a * dim*sqrt;
		for(int k=0; k<sqrt; k++) {
			for(int j=0; j<sqrt; j++) {
				temporary.add( variables.get(b));
				b++;				
			}
			b +=dim-sqrt;
		}
		list.add( new SudokuConstraint(temporary) );
		return list;
	}

	//aggiorno dominio di una variabile
	public void setDomain(Variable v, List<Object> d) {
		domains.replace(v, d);
	}
	
	public List<Object> getDomain(Variable v) {
		return domains.get(v);
	}
	
	@Override
	public List<Variable> variables() {
		return variables;
	}

	@Override
	public Assignment assignment() {
		return assignment;
	}

	@Override
	public int getSize() {
		return variables.size();
	}

	//operazione di preprocessing relativa a eventuali valori già impostati in input
	//aggiornato lo stato generale dell'assignment devo comunque andare a aggiorare i domini delle variabili
	//nel caso in cui io stia usando un algoritmo di ricerca basato sull'analisi dei domini
	
	//funzione di preprocessing relativa alla risoluzione tramite metodo forward checking
	//aggiorno il dominio dei neighbours delle variaibli assegnate rimuovendo i soli valori di queste variabili
	@Override
	public void preProcess() {
		Map<Variable, Object> map = this.assignment().map();
		List<Object> temp;
		
		for( Variable key : map.keySet() ) {
			//per la variabili assegnata imposto il dominio al solo valore assegnato 

			Object o = map.get(key);
			temp = new LinkedList<>(Arrays.asList(o));
			domains.replace(key, temp);
			
			//per i vicini rimuovo dai rispettivi domini il valore assegnato alla variabili (superfluo se uso ac3 anzichè forward immagino)
			for(Variable n : key.neighbours()) {
				
				if( domains.get(n).contains(o) ) {
			
					temp = new LinkedList<>(domains.get(n));
					temp.remove(o);
					domains.replace(n, temp);
				}				
			}
		}
	}
	
	//funzione di preprocessing relativa alla risoluzione tramite metodo AC3
	//aggiorno il dominio dei neighbours tramite metodo basato su arc-consistency
	@Override
	public void preProcessAC3() {
		Map<Variable, Object> map = this.assignment().map();
		Stack<Variable> queue = new Stack<>();
		List<Object> temp;
		
		for( Variable key : map.keySet() ) {
			//per la variabili assegnata imposto il dominio al solo valore assegnato 

			Object o = map.get(key);
			temp = new LinkedList<>(Arrays.asList(o));
			domains.replace(key, temp);
			
			//aggiungo alla coda delle variabili su cui controllare l'arc-consistency tutte quelle preassegnate nel problema
			queue.add(key);
		}
		
		while(!queue.isEmpty()) {
			Variable temporary = queue.pop();
			for(Constraint c : temporary.getConstraints()) {
				c.domainconsistent(this, queue, temporary);
			}
		}
	}

}
