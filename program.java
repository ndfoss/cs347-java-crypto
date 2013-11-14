import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

//This is the java file that will run the program
/**
 * TODO
 * This file opens a window prompting the user to select 
 * whether they will encrypt a text file or decrypt one
 * 
 * Whichever button is clicked calls the corresponding class
 * with an action event handler
 */

public class program extends JPanel{
	

	private static final long serialVersionUID = -3117772857731837940L;
	
	public program(){
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Encryption");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new program());
		frame.setPreferredSize(new Dimension(250,250));
        frame.pack();
        frame.setVisible(true);
		
		
	}
}