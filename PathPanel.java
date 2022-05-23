import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class PathPanel extends JPanel {

	private JPanel panel;
	private JList list;
	private ArrayList<TraceState> boards;
	
	
	public PathPanel (JPanel pathLabel, ArrayList<TraceState> boards) {
		panel = pathLabel;
		this.boards = boards;
		
			String[] paths = new String[boards.size()];
			
			
			for (int i=0; i<boards.size(); i++) {
				paths[i] = "Path " + (i+1);
			}
		list = new JList(paths);
		list.addListSelectionListener(new ListListener());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.setPreferredSize(new Dimension(boards.get(0).getBoard().numCols()*50, boards.get(0).getBoard().numRows()*50));
		

		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, list);
		add(sp);
		setBackground(Color.white);
	}
	
	
	
	private void makeBoard(CircuitBoard board) {
		int rows = board.numRows();
		int cols = board.numCols();
		panel.removeAll();
		panel.setLayout(new GridLayout(rows, cols));
		Border blackBorder = BorderFactory.createLineBorder(Color.black);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				JLabel label = new JLabel (String.valueOf(board.charAt(i, j)), JLabel.CENTER);
				if (board.charAt(i, j) == 'T') {
					label.setForeground(Color.red);
					label.setFont(new Font("sanserif", Font.BOLD, 24));
				}
				else if (board.charAt(i, j) == '1' || board.charAt(i, j) == '2'){
					label.setForeground(new Color(0,128,0));
					label.setFont(new Font("sanserif", Font.BOLD, 24));
				}
				else {
					label.setForeground(Color.black);
					label.setFont(new Font( "sanserif", Font.PLAIN, 24));
				}
				
				label.setBorder(blackBorder);
				panel.add(label);
				
			}
		}
		panel.revalidate();
		panel.repaint();
	}
	
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (list.isSelectionEmpty()) {
	            
			}
	         else
	         {
	        	String word = (String)list.getSelectedValue();
	            makeBoard(boards.get(Integer.parseInt(word.substring(5))-1).getBoard());
	         }
			
		}
		
	}
}
