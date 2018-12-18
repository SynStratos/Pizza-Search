package connectfour;

import java.util.List;

public class Heuristic3 extends Heuristic {
    public Heuristic3(List<List<int[]>> toCheck, FourBoard b) {
        super(toCheck, b);
    }
    //lo score in questo caso valuta anche le caselle vuote a vantaggio di uno o dell'altro giocatore
    //nel momento in cui la mia mossa risulta inutile per fermare l'avversario in quanto se la somma delle caselle occupate da lui più la somma delle caselle vuote tra le sue è uguale a 4 non valuto la posizione
    @Override
    public int value() {
        int x = 0;
        int y = 0;
        int score = 0;

        for( List<int[]> check : toCheck) {
            int countpc = 0;
            int countpl = 0;
            int empty = 0;

            for(int[] i : check) {
                x = i[0];
                y = i[1];

                if(b.board[x][y] == COMPUTER) {
                    //nel momento in cui le caselle del mio avversario più quelle libere tra le sue è >=4 vorrà dire che la mia mossa che sto valutando non è utile a bloccare il suo stato
                    if( (empty+countpl) >=4) break;
                    if(countpl > 0) {
                        //se la condizione precedente non è soddisfatta passo ad azzerare il counter delle caselle del giocatore avversario in quanto saranno inutili per il suo score
                        //inoltre azzero il counter degli spazi vuoti che in questo caso era a "vantaggio" dell'avversario
                        countpl=0;
                        empty=0;
                    }
                    //incremento il counter del giocatore in corso
                    countpc++;
                    //se il counter ha già raggiunto 4 so che corrisponde ad una condizione di vittoria e interrompo il ciclo
                    if(countpc == 4) break;

                }
                if(b.board[x][y] == PLAYER) {
                    //nel momento in cui le caselle del mio avversario più quelle libere tra le sue è >=4 vorrà dire che la mia mossa che sto valutando non è utile a bloccare il suo stato
                    if( (empty+countpc) >=4) break;
                    if(countpc > 0) {
                        //se la condizione precedente non è soddisfatta passo ad azzerare il counter delle caselle del giocatore avversario in quanto saranno inutili per il suo score
                        //inoltre azzero il counter degli spazi vuoti che in questo caso era a "vantaggio" dell'avversario
                        countpc=0;
                        empty=0;
                    }
                    //incremento il counter del giocatore in corso
                    countpl++;
                    //se il counter ha già raggiunto 4 so che corrisponde ad una condizione di vittoria e interrompo il ciclo
                    if(countpl == 4) break;
                }
                if(b.board[x][y] == 0){
                    empty += 1;

                }
            }
            //al termine del controllo per la riga/colonna/diagonale verifico che la somma delle caselle vuote "positive" più quelle del giocatore di cui sto contando le caselle sia >= 4
            //altrimenti quella valutazione sarà superflua per quel giocatore
            //effettuare nuovamente il controllo in questo momento è necessario dato che le caselle vuote potrebbero presentarsi anche alla fine della "riga" valutata
            if(countpc + empty < 4) countpc=0;

            if(countpl + empty < 4) countpl=0;

            score += ((countpc)^2 * 1) + ((countpl)^2 *(-1));
        }
        return score;
    }
}
