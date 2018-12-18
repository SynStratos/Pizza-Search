package tavoli;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import constraints.*;

public class LitigioConstraint extends Constraint{
	
	List<Variable> litigio = new LinkedList<>();
	
	public LitigioConstraint( List<Variable> familiari) {
		this.litigio.addAll(familiari);
	}
	
	@Override
	public boolean isSatisfied(Assignment asgn) {
		Object temporary_table = null;
		for(Variable nemico : litigio) {
		
			Object tavolo = asgn.map().get(nemico);
			if(tavolo!= null && temporary_table == null) temporary_table=tavolo;
			else {
				if (tavolo!= null && tavolo == temporary_table) return false;
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
