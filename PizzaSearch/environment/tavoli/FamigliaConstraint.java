package tavoli;

import constraints.*;
import java.util.*;

public class FamigliaConstraint extends Constraint{

	List<Variable> familiari = new LinkedList<>();
	
	public FamigliaConstraint( List<Variable> familiari) {
		this.familiari.addAll(familiari);
	}
	
	@Override
	public boolean isSatisfied(Assignment asgn) {
		Object temporary_table = null;
		for(Variable familiare : familiari) {
		
			Object tavolo = asgn.map().get(familiare);
			if(tavolo!= null && temporary_table == null) temporary_table=tavolo;
			else {
				if (tavolo!= null && tavolo != temporary_table) return false;
			}
			
		}
		
		return true;
	}

	@Override
	public List<Variable> variables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void domainconsistent(CSP csp, Stack<Variable> queue, Variable v) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
