import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class Program extends JPanel{
	

	private static final long serialVersionUID = -3117772857731837940L;
	
	public Program(){
		final JPanel initialScreen = new JPanel(new GridLayout());
		final Box selectBox = new Box(BoxLayout.Y_AXIS);
		final JLabel encryptLabel = new JLabel("Encrypt Homework");

		final JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the encryption by creating new encrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		final JLabel decryptLabel = new JLabel("Decrypt Submission");
		final JButton decryptButton = new JButton("Decrypt");
		decryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the decryption by creating new encrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame decryptFrame = new JFrame();
				decryptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				decryptFrame.getContentPane().add(new Decrypt());
				decryptFrame.setPreferredSize(new Dimension(500,500));
		        decryptFrame.pack();
		        decryptFrame.setVisible(true);
				
			}
		});
		
		initialScreen.add(encryptLabel);
		initialScreen.add(decryptLabel);
		selectBox.add(encryptButton);
		selectBox.add(decryptButton);
		initialScreen.add(selectBox);
		add(initialScreen);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Encryption");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Program());
		frame.setPreferredSize(new Dimension(500,500));
        frame.pack();
        frame.setVisible(true);
		
		
	}
}