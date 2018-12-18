package constraints;

import java.util.List;

public class DegreeHeuristic {

	public Variable DH(List<Variable> variables, Assignment assignment) {
		Variable DHvariable = null;
		
		int max=0;
		int num=0;
		
		
		for(Variable v : variables) {
			if(!assignment.map().containsKey(v)) {
				
				num = v.getConstraints().size();
				if(num > max) {
					max = num;
					DHvariable = v;
				}
			}
		}
		
		return DHvariable;
	}
	
}
