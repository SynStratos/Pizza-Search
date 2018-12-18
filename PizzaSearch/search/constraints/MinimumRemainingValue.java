package constraints;

import java.util.*;

public class MinimumRemainingValue {

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
	
	
	
}