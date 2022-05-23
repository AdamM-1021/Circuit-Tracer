

import java.util.ArrayList;


import javax.swing.JFrame;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CircuitPanel extends JFrame {


	public CircuitPanel(ArrayList<TraceState> boards) {
		setTitle("Circuit Tracer");
		JPanel gridLabel = new JPanel();


		PathPanel pathList = new PathPanel(gridLabel, boards);
		
		
		
		getContentPane().add(pathList);
		pack();
		
		
	}
	
	
	
}
