import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); //create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		// See https://en.wikipedia.org/wiki/Usage_message for format and content guidance
		System.out.println("Usage: java CircuitTracer [-s | -q] [-c | -g] [filename]");
		System.out.println("[-s | -q] : Stack or Queue storage model");
		System.out.println("[-c | -g] : console or GUI output type");
		System.out.println("[filename] : The starting board you want");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		boolean isStack;
		boolean useGUI;
		//TODO: parse and validate command line args - first validation provided
		if (args.length != 3) {
			printUsage();
			return; //exit the constructor immediately
		}
		if (args[0].equals("-s")) {
			isStack = true;
		}
		else if (args[0].equals("-q")) {
			isStack = false; 
		}
		else {
			System.out.println("storage");
			printUsage();
			return;
		}
		
		if (args[1].equals("-c")) {
			useGUI = false;
		}
		else if (args[1].equals("-g")) {
			useGUI = true;
		}
		else {
			printUsage();
			return;
		}
		
		Storage<TraceState> stateStore;
		CircuitBoard board;
		try {
			board = new CircuitBoard(args[2]);
		} 
		catch (FileNotFoundException e) {
			System.out.println(e);
			return;
		}
		catch (InvalidFileFormatException e) {
			System.out.println(e);
			return;
		}
		
		
		

		if (isStack) {
			stateStore = Storage.getStackInstance();
		}
		else {
			stateStore = Storage.getQueueInstance();
		}

		

		ArrayList<TraceState> bestPaths = new ArrayList<TraceState>();
		TraceState currentState;
		if (board.isOpen(((int)board.getStartingPoint().getX()+1), (int)board.getStartingPoint().getY())) {
			stateStore.store(new TraceState(board, ((int)board.getStartingPoint().getX()+1), (int)board.getStartingPoint().getY()));
		}
		
		if (board.isOpen(((int)board.getStartingPoint().getX()-1), (int)board.getStartingPoint().getY())) {
			stateStore.store(new TraceState(board, ((int)board.getStartingPoint().getX()-1), (int)board.getStartingPoint().getY()));
		}
		
		if (board.isOpen(((int)board.getStartingPoint().getX()), (int)board.getStartingPoint().getY()+1)) {
			stateStore.store(new TraceState(board, ((int)board.getStartingPoint().getX()), (int)board.getStartingPoint().getY()+1));
		}
		
		if (board.isOpen(((int)board.getStartingPoint().getX()), (int)board.getStartingPoint().getY()-1)) {
			stateStore.store(new TraceState(board, ((int)board.getStartingPoint().getX()), (int)board.getStartingPoint().getY()-1));
		}
		
		while (!stateStore.isEmpty()) {
			currentState = stateStore.retrieve();
			
			if (currentState.isComplete()) {
				if(bestPaths.isEmpty() || bestPaths.get(0).pathLength() == currentState.pathLength()) {
					bestPaths.add(currentState);
				}
				else if (!bestPaths.isEmpty() && currentState.pathLength() < bestPaths.get(0).pathLength()) {

					bestPaths.clear();
					bestPaths.add(currentState);
				}
			}
			
			else
			{
				if (currentState.isOpen((currentState.getRow() + 1), currentState.getCol())) {
					stateStore.store(new TraceState(currentState, currentState.getRow() + 1, currentState.getCol()));
				}
				
				if (currentState.isOpen((currentState.getRow()), currentState.getCol() + 1)) {
					stateStore.store(new TraceState(currentState, currentState.getRow(), currentState.getCol() + 1));
				}
				
				if (currentState.isOpen((currentState.getRow() - 1), currentState.getCol())) {
					stateStore.store(new TraceState(currentState, currentState.getRow() - 1, currentState.getCol()));
				}
				
				if (currentState.isOpen((currentState.getRow()), currentState.getCol() - 1)) {
					stateStore.store(new TraceState(currentState, currentState.getRow(), currentState.getCol() - 1));
				}
			}
		}
		
		//TODO: output results to console or GUI, according to specified choice
		if (!useGUI) {
			for (int i = 0; i < bestPaths.size(); i++) {
				System.out.println(bestPaths.get(i));
			}
		}
		else {
			if (bestPaths.size() == 0) {
				System.out.println("No valid paths");
			}
			else {
				
				JFrame frame = new CircuitPanel(bestPaths);
				System.out.println("GUI initialized");
				frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				frame.setName("Circuit Tracer");
				frame.setVisible(true);
			}
		}
	}
	
} // class CircuitTracer
