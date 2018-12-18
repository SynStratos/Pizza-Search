# PIZZA SEARCH

##### Progetto in java che riesca ad inglobare tutte le funzioni di ricerca per l'intelligenza artificiale. L'obiettivo è quello di rendere le strutture il più versatili possibili grazie a classi molto generiche e classi abstract.

### STRUTTURA DEL PROGETTO

* **src**
    * **main**: classe che gestisce l'interfaccia in shell della scelta del "gioco" e degli eventuali parametri ad essi collegati

* **environment** (pacchetto contenente le classi specifiche necessarie per ogni problema)
    * **PROBLEMI DI RICERCA INFORMED E UNINFORMED**
        * **eightpuzzle** (gioco in cui si devono ordinare le caselle contenute nel puzzle in ordine crescente)
            * **PuzzleBoard**: classe che gestisce l'intera costruzione dell'albero del gioco
            * **PuzzleHeuristic**: classe per la gestione della valutazione dello stato attuale del gioco
            * **RunPuzzle**: classe per l'avvio della risoluzione del problema impiegando l'algoritmo scelto
        * **mazerun** (gioco in cui si deve raggiungere la casella corrispondente all'uscita evitando gli ostacoli)
            * **MazeBoard**: classe che gestisce l'intera costruzione dell'albero del gioco
            * **MazeHeuristic**: classe per la gestione della valutazione dello stato attuale del gioco
            * **RunMaze**: classe per l'avvio della risoluzione del problema impiegando l'algoritmo scelto
    * **PROBLEMI DI RICERCA CON CONSTRAINTS (CSP)**
        * **sudoku**(risoluzione del gioco Sudoku data una determinata board)
            * **RunSudoku**: classe per la costruzione dell'input e per l'avvio del gioco
            * **SudokuConstraint**: classe per la definizione del vincolo relativo alla necessaria differenza dei valori inseriti nelle caselle passate come parametro
            * **SudokuCSP**: classe principale contenente tutti i metodi per la gestione del problema e delle sue variabili
        * **tavoli** (3 tavoli da 6 posti, gruppi di persone della stessa famiglia allo stesso tavolo, gruppi di persone che hanno litigato a tavoli diversi)
            * **FamigliaConstraint**: classe per la definizione del vincolo relativo ai familiari
            * **LitigioConstraint**: classe per la definizione del vincolo relativo ai litigi
            * **PostiConstraint**: classe per la definizione del vincolo relativo al numero di posti disponibili per ogni tavolo
            * **TavoliBoard**: classe per la costruzione dell'input e per l'avvio del gioco
            * **TavoliCSP**: classe principale contenente tutti i metodi per la gestione del problema e delle sue variabili
	* **PROBLEMI DI RICERCA ADVERSARIAL DETERMINISTICA** 
        * **tictactoe** (gestione di una partita di Tris)
            * **TicTacToeBoard**: classe utile a tener traccia dello stato attuale del gioco
            * **TicTacToeGame**: classe principale utile all'intera gestione del gioco, alla generazione delle mosse e alla valutazione dello stato
            * **TicTacToeMove**: classe utile per la generazione della mossa
            * **TicTacToeSetup**: classe per la gestione dei turni del gioco
        * **connectfour** (gestione di una partita di ConnectFour)
            * **FourBoard**: classe utile a tener traccia dello stato attuale del gioco
            * **FourGame**: classe principale utile all'intera gestione del gioco, alla generazione delle mosse e alla valutazione dello stato
            * **FourMove**: classe utile per la generazione della mossa
            * **FourSetup**: classe per la gestione dei turni del gioco
    * **PROBLEMI DI RICERCA ADVERSARIAL NON DETERMINISTICA**
        * **mymaze** (gestione di una partita ad un gioco che consiste in un mazerun a due giocatori in cui il numero di caselle ad ogni mossa è deciso dal risultato di un dado a 4 facce)
            * **MBoard**: classe utile a tener traccia dello stato attuale del gioco
            * **MGame**: classe principale utile all'intera gestione del gioco, alla generazione delle mosse e alla valutazione dello stato
            * **MMove**: classe utile per la generazione della mossa
            * **MSetup**: classe per la gestione dei turni del gioco

* **search** (pacchetto contenente tutte le classi abstract e gli algoritmi utilizzabili da qualsiasi problema che le necessiti)
	* **ALGORITMI DI RICERCA INFORMED E INFORMED**
        * **informed** (algoritmi di ricerca con informazioni note)
            * **Astar**: classe che implementa l'algoritmo di ricerca informata A*
            * **GreedyBestFirst**: classe che implementa l'algoritmo di ricerca informata Greedy Best First
        * **uninformed** (algoritmi di ricerca non informata)
            * **BreadthFirst**: classe che implementa l'algoritmo di ricerca non informata Breadth First
            * **DepthFirst**: classe che implementa l'algoritmo di ricerca non informata Depth First
            * **DepthLimited**: classe che implementa l'algoritmo di ricerca non informata Depth Limited
            * **IterativeDeepening**: classe che implementa l'algoritmo di ricerca non informata Iterative Depth Limited
            * **UniformCost**: classe che implementa l'algoritmo di ricerca non informata Uniform Cost
        * **framework** (classi utili alla costruzione dei problemi)
            * **Board**: classe per la gestione dello stato e del nodo attuale nello studio del problema
            * **Heuristic**: classe per la valutazione euristica dello stato attuale del problema
            * **Node**: classe contenente tutte le proprietà e tutti i metodi correlati ad un nodo del problema
        * **utils** (classi utili alla risoluzione del problema, nel dettaglio comparator per costo e costo stimato del nodo)
    * **ALGORITMI DI RICERCA LOCALE**
        * **local**
            * **HillClimbing**: classe che implementa l'algoritmo di ricerca locale Hill Climbing
            * **SimulatedAnnealing**: classe che implementa l'algoritmo di ricerca locale Simulated Annealing
    * **ALGORITMI DI RICERCA CON CONSTRAINTS (CSP)**
        * **constraints**
            * **AC3**: classe che implementa l'algoritmo di ricerca con vincoli AC3
            * **Assignment**: classe contenente i metodi per la gestione e il controllo dello stato attuale del problema
            * **BackTracking**: classe che implementa l'algoritmo di ricerca con vincoli Back Tracking
            * **Constraing**: classe abstract contenente i metodi relativi al controllo del soddisfacimento dei vincoli del problema
            * **CSP**: classe abstract contenente i principali metodi per la gestione di un problema con vincoli
            * **DegreeHeuristic**: classe contenente il metodo di selezione della variabile coinvolta nel maggior numero di vincoli
            * **ForwardChecking**: classe che implementa l'algoritmo di ricerca con vincoli Forward Checking
            * **ImprovedBackTracking**: classe che implementa l'algoritmo di ricerca con vincoli Back Tracking utilizzante funzioni perfezionate per la selezione di variabili e valori
            * **LeastConstrainingValue**: (ancora da implementare)
            * **MinimumRemainingValue**: classe contenente il metodo di selezione della variabile con minor numero di valori assegnabili
            * **Variable**: classe contenente le funzioni necessarie per la gestione di una variabile del gioco
	* **ALGORITMI DI RICERCA ADVERSARIAL**
        * **adversarial** (algoritmi per i giochi con avversario con decisioni deterministiche)
            * **AlphaBeta**: classe che implementa l'algoritmo di ricerca decisionale Alpha-Beta Pruning
            * **Game**: classe abstract contenente tutti i principali metodi per la gestione del gioco
            * **GameBoard**: classe abstract relativa allo stato attuale del gioco
            * **LimitedAlphaBeta**: classe che implementa l'algoritmo di ricerca decisionale Alpha-Beta Pruning con profondità limitata
            * **LimitedMiniMax**: classe che implementa l'algoritmo di ricerca decisionale MiniMax con profondità limitata
            * **MiniMax**: classe che implementa l'algoritmo di ricerca decisionale MiniMax
            * **Move**: classe abstract relativa alla mossa da effettuare nel gioco
        * **adversarial_p** (algoritmi per i giochi con avversario con decisioni influenzate da risultati non deterministici)
            * **ExpectiMiniMax**: classe che implementa l'algoritmo di ricerca decisionale ExpectiMiniMax
            * **Game**: classe abstract contenente tutti i principali metodi per la gestione del gioco
            * **GameBoard**: classe abstract relativa allo stato attuale del gioco
            * **Move**: classe abstract relativa alla mossa da effettuare nel gioco


**FEATURE DA IMPLEMENTARE:**

- [ ] Risoluzione strade mappa 

- [ ] LeastConstrainingValue (non utilizzabile in sudoku in quanto constraints non binari)
- [ ] Risoluzione colora mappa
- [ ] Risoluzione nqueens