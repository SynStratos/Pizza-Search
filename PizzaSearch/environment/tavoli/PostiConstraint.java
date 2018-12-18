package tavoli;

import java.util.*;

import constraints.*;

public class PostiConstraint extends Constraint {

	List<Variable> all = new ArrayList<>();
	
	public PostiConstraint(List<Variable> all) {
		this.all = all;
	}
	
	@Override
	public boolean isSatisfied(Assignment asgn) {
		Object a = (Integer) 1;
		Object b = (Integer) 2;
		Object c = (Integer) 3;
		
		int counta = 0;
		int countb = 0;
		int countc = 0;
		
		
		for(Variable v : all) {
			Object temp = asgn.map().get(v);
			if(temp != null) {
				if(temp.equals(a)) {
					counta++;
					if (counta > 6) return false;
				}
				if(temp.equals(b)) {
					countb++;
					if (countb > 6) return false;
				}
				if(temp.equals(c)) {
					countc++;
					if (countc > 6) return false;
				}
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
