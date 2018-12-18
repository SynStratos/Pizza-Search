import java.util.Scanner;

import connectfour.FourSetup;
import eightpuzzle.RunPuzzle;
import mazerun.RunMaze;
import mymaze.MBoard;
import mymaze.MSetup;
import sudoku.RunSudoku;
import tavoli.TavoliBoard;
import tictactoe.TicTacToeSetup;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String s;
		
		String algorithm;
		System.out.println("Che test vuoi eseguire?");
		System.out.println("0 - Eight Puzzle");
		System.out.println("1 - Maze Run");
		System.out.println("2 - Sudoku");
		System.out.println("3 - Tavoli (BackTracking)");
		System.out.println("4 - Tic Tac Toe");
		System.out.println("5 - Connect Four");
		System.out.println("6 - My Maze");
		s = scan.nextLine();
		switch (s) {
		case "0" : {
			String begin;
			System.out.println("Scegli la difficoltà: ");
			System.out.println("0 - easy");
			System.out.println("1 - medium");
			System.out.println("2 - hard");
			System.out.println("3 - impossible (solo informed)");
			s = scan.nextLine();
			switch (s) {
			case "0" : begin = "123456078"; break;
			case "1" : begin = "023156478"; break; //"103425786"; break; //con depth first va in loop //prima funzionava
			case "2" : begin = "103425786"; break; //non funziona più --> prima ci metteva molte iterazioni adesso esplode
			case "3" : begin = "276438510"; break; //consigliato solo informed -> troppe iterazioni
			default : return;
			}
			System.out.println("Scegli l'algoritmo da utilizzare: ");
			System.out.println("---Uninformed---");
			System.out.println("0 - Breadth First Search");
			System.out.println("1 - Depth First Search");
			System.out.println("2 - Depth Limited Search");
			System.out.println("3 - Iterative Deepening Search");
			System.out.println("4 - Uniform Cost Search");
			System.out.println("---Informed---");
			System.out.println("5 - Greedy Best First Search");
			System.out.println("6 - AStar Search");
			System.out.println("---Local---");
			System.out.println("7 - Hill Climbing");
			System.out.println("8 - Simulated Annealing");
			algorithm = scan.nextLine();
			RunPuzzle new_game = new RunPuzzle(begin, algorithm);
			new_game.run();
			break;
		}
		case "1" : {
			int dim;
			int walls;
			Integer[] begin = new Integer[2];
			Integer[] end = new Integer[2];
			System.out.println("Scegli la dimensione della griglia (l): ");
			dim = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli il punto di inizio (row): ");
			begin[0] = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli il punto di inizio (column): ");
			begin[1] = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli il punto di arrivo (row): ");
			end[0] = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli il punto di arrivo (column): ");
			end[1] = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli il numero di ostacoli: ");
			walls = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli l'algoritmo da utilizzare: ");
			System.out.println("---Uninformed---");
			System.out.println("0 - Breadth First Search");
			System.out.println("1 - Depth First Search");
			System.out.println("2 - Depth Limited Search");
			System.out.println("3 - Iterative Deepening Search");
			System.out.println("4 - Uniform Cost Search");
			System.out.println("---Informed---");
			System.out.println("5 - Greedy Best First Search");
			System.out.println("6 - AStar Search");
			algorithm = scan.nextLine();
			RunMaze new_game = new RunMaze(dim, walls, begin, end, algorithm);
			new_game.run();
		} break;
		case "2" : {
			System.out.println("Scegli la dimensione della griglia (4 o 9): ");
			int dim = Integer.parseInt( scan.nextLine() );
			System.out.println("Scegli l'algoritmo da utilizzare: ");
			System.out.println("0 - BackTracking");
			System.out.println("1 - Improved BackTracking");
			System.out.println("2 - Forward Checking");
			System.out.println("3 - Arc Consistency");
			algorithm = scan.nextLine();
			RunSudoku sudoku = new RunSudoku(dim, algorithm);
			break;
		}
		case "3" : {
			TavoliBoard board = new TavoliBoard();
			board.run();
			break;
		}
		case "4" : {
			System.out.println("Scegli chi comincia la partita (Tu: 0 Pc: 1): ");
			int begin = Integer.parseInt( scan.nextLine() );
			TicTacToeSetup board = new TicTacToeSetup(begin);
			board.run();
			break;
		}
		case "5" : {
			System.out.println("Scegli chi comincia la partita (Tu: 0 Pc: 1): ");
			int begin = Integer.parseInt( scan.nextLine() );
			FourSetup board = new FourSetup(begin);
			board.run();
			break;
		}
		case "6" :{
			System.out.println("Scegli chi comincia la partita (Tu: 0 Pc: 1): ");
			int begin = Integer.parseInt( scan.nextLine() );
			MSetup board = new MSetup(begin);
			board.run();
			break;
		}
		default : return;
		}

	}
}
