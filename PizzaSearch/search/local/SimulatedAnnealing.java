package local;

import framework.Board;
import framework.Heuristic;
import framework.Node;
import utils.HeuristicComparator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealing {
    private Node root;
    private Node goal;
    private Board board;
    private Heuristic heuristic;
    private int step = -1;
    private boolean reached = true;

    Stack<Node> queue = new Stack<Node>();
    List<Node> explored = new ArrayList<Node>();

    public SimulatedAnnealing(Node root, Node goal, Board board, Heuristic heuristic) {
        this.root=root;
        this.goal=goal;
        this.board=board;
        this.heuristic=heuristic;
    }

    public Node run(){

        Node result = null;


        root.setHeuristic(heuristic.hCost());
        queue.add(root);
        Node current = new Node(null);

        while(!queue.isEmpty())
        {



            //Queue<Node> child = new PriorityQueue<Node>(10, new HeuristicComparator());

            List<Node> child = new ArrayList<>();

            current = queue.pop();
            explored.add(current);


            step++;
            reached = true;

            for( int i=0; i<goal.getState().length; i++) {
                if(!current.getState()[i].equals(goal.getState()[i])) reached = false;
            }

            if( reached ) {
                System.out.println("Steps: " + step);
                return current;
            }

            board.parent = current;

            ArrayList<Node> bb = board.buildChildren();
            Iterator<Node> it = bb.iterator();
            // per ogni nodo child ottenuto aggiorno il valore del nodo da esaminare e aggiungo al nodo child da inserire in coda il rispettivo valore di costo stimato
            // il comparator "HeuristicComparator" ordinerà gli elementi della coda in base al costo euristico inferiore
            while(it.hasNext()) {
                Node t = it.next();
//                if(!checkExplored(t)) {
                    heuristic.setNode(t);
                    t.setHeuristic(heuristic.hCost());
                    child.add(t);
//                }
            }
            double temp = 10;
            double alpha = 0.001;

            while(temp > 0.001){
                temp = temp - temp*alpha;
                Node nextnode = child.get( ThreadLocalRandom.current().nextInt(0, child.size()));

                double deltaE = nextnode.getHeuristic() - current.getHeuristic();
                if (deltaE < 0) {
                    queue.push(nextnode);
                    break;
                }
                else {
                    double probability = Math.pow(deltaE, temp);
                    if( probability > ThreadLocalRandom.current().nextInt(0, 1 + 1)) {
                        queue.push(nextnode);
                        break;
                    }
                }

            }


        }
        if(current!=null) return current;
        System.out.println("No result found in "+step+" steps.");
        System.exit(0);
        return null;
    }

    private boolean checkExplored(Node node) {

        for(Node iterated : explored) {
            for( int i=0; i<goal.getState().length; i++) {
                if(node.getState()[i].equals(iterated.getState()[i])) return true;
            }
        }
        return false;
    }

}
