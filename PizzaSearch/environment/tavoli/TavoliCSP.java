package tavoli;

import java.util.*;

import constraints.*;

public class TavoliCSP extends CSP{

	
	List<Variable> partecipanti;
	Map<Variable, List<Object>> domains;
	
	Assignment stato;
	
	List<Object> dominio = new LinkedList<>();
	
	public TavoliCSP() {
		
		partecipanti = new LinkedList<>();
		domains = new HashMap<Variable, List<Object>>();
		stato = new Assignment();
		stato = new Assignment();
		
		dominio.add(1);
		dominio.add(2);
		dominio.add(3);
		
		String[] ospiti = {"Antonella", "Domenico", "Raffella", "Tommaso", "Vincenzo", "Azzurra", "Cristiano", "Francesca", "Luigi", "Giovanni", "Marcella", "Daniela", "Nunzio", "Leonardo", "Silvia"};
		for(int i=0; i<ospiti.length; i++) {
			Variable persona = new Variable(ospiti[i]);
			partecipanti.add(persona);
			domains.put(persona, dominio);
		}
		
		Constraint allCon = new PostiConstraint(partecipanti);
		
		for(Variable v : partecipanti) {
			v.addConstraint(allCon);
		}
				
		List<Variable> famiglia_1 = new LinkedList<>();
		
		famiglia_1.add(partecipanti.get(0));
		famiglia_1.add(partecipanti.get(1));
		famiglia_1.add(partecipanti.get(2));
		famiglia_1.add(partecipanti.get(3));
		famiglia_1.add(partecipanti.get(4));
		
		List<Variable> famiglia_2 = new LinkedList<>();
		famiglia_2.add(partecipanti.get(5));
		famiglia_2.add(partecipanti.get(6));
		famiglia_2.add(partecipanti.get(7));
		famiglia_2.add(partecipanti.get(8));
		
		List<Variable> litigio_1 = new LinkedList<>();
		litigio_1.add(partecipanti.get(9));
		litigio_1.add(partecipanti.get(10));
		
		List<Variable> litigio_2 = new LinkedList<>();

		litigio_2.add(partecipanti.get(11));
		litigio_2.add(partecipanti.get(10));
		
		List<Variable> litigio_3 = new LinkedList<>();
		litigio_3.add(partecipanti.get(8));
		litigio_3.add(partecipanti.get(13));
		
		
		//costruzione dei constraints
		for(Variable v : famiglia_1) {
			v.addConstraint( new FamigliaConstraint(famiglia_1));
		}
		
		for(Variable v : famiglia_2) {
			v.addConstraint( new FamigliaConstraint(famiglia_2));
		}
		
		for(Variable v : litigio_1) {
			v.addConstraint(new LitigioConstraint(litigio_1));
		}
		
		for(Variable v : litigio_2) {
			v.addConstraint(new LitigioConstraint(litigio_2));
		}
		
		for(Variable v : litigio_3) {
			v.addConstraint(new LitigioConstraint(litigio_3));
		}
	
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
			return partecipanti;
		}

		@Override
		public Assignment assignment() {
			return stato;
		}

		@Override
		public int getSize() {
			return partecipanti.size();
		}
		


	@Override
	public void preProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preProcessAC3() {
		// TODO Auto-generated method stub
		
	}
	
	
}
