package constraints;

import java.util.*;

public abstract class CSP {
	
	//funzione che mi restituisce l'elenco delle variabili
	public abstract List<Variable> variables(); 
	
	//funzione che preleva lo stato attuale degli assignment
	public abstract Assignment assignment();
		
	//funzione che restituisce il numero delle variabili
	public abstract int getSize();
	
	//nel momento in cui ho dei dati già inseriti all'interno del problema, devo prevedere come gestirli	
	//in caso di *FORWARD CHECKING*
	public abstract void preProcess();
	//in caso di "ARC CONSISTENCY*
	public abstract void preProcessAC3();
	
	//aggiungo variabile e relativo dominio alla mappa
	public abstract void setDomain(Variable v, List<Object> d);
	
	//prelevo dominio della variabile data come parametro
	public abstract List<Object> getDomain(Variable v);
	

}
