package sudoku;

import java.util.LinkedList;
import java.util.List;

import constraints.*;

public class RunSudoku{

	private String input;
	private SudokuCSP sudoku;
	private List<Object> domain;
	private String input4 = 
			  "3040"
			+ "0103"
			+ "2300"
			+ "1002";
	private String input9 =
			  "004300209"
			+ "005009001"
			+ "070060043"
			+ "006002087"
			+ "190007400"
			+ "050083000"
			+ "600000105"
			+ "003508690"
			+ "042910300";
	
	private String method;
	
	public RunSudoku(int dim, String algorithm) {
		this.method = algorithm;
		if(dim == 4) input = input4;
		else if(dim == 9) input = input9;
		else { System.out.print("Unexpected size"); System.exit(0); }
		
		//creo dominio generale di ogni variabile con tutti i possibili valori (da 1 a dim)
		domain = new LinkedList<>();
		for(int i=0; i<dim; i++) domain.add(i, i+1);
		
		sudoku = new SudokuCSP(domain, input, dim);

		Assignment solution = null;
		
		switch(method) {
		case "0" :	{
			BackTracking bt = new BackTracking();
			solution = bt.solve(sudoku, sudoku.assignment());
			break;
		}
		case "1" :	{
			ImprovedBackTracking ibt = new ImprovedBackTracking();
			solution = ibt.solve(sudoku, sudoku.assignment());
			break;
		}
		case "2" : {
			sudoku.preProcess();
			ForwardChecking fc = new ForwardChecking();
			solution = fc.solve(sudoku, sudoku.assignment());
			break;
		}
		case "3" : {
			sudoku.preProcessAC3();
			AC3 ac = new AC3();
			solution = ac.solve(sudoku, sudoku.assignment());
			break;
		}
		default :	{
			System.out.println("unexpected algorithm");
			System.exit(0);
		}
		}
		
		int i=0;
		for(Variable v : sudoku.variables()) {
			System.out.print( solution.map().get(v) );
			i++;
			if(i%dim==0) System.out.println(" ");
		}	
	}
}
