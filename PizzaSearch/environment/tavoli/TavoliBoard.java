package tavoli;

import constraints.*;

public class TavoliBoard {

	public void run() {
		CSP tavoli = new TavoliCSP();
		
		BackTracking bt = new BackTracking();
		Assignment solution = bt.solve(tavoli, tavoli.assignment());
		
		for(Variable v : tavoli.variables()) {
			System.out.print(v.value().toString());
			System.out.println(" siede al tavolo: " + solution.map().get(v) );
			
		}	
	}
}
