import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
		JPanel initialScreen = new JPanel(new GridLayout());
		Box selectBox = new Box(BoxLayout.Y_AXIS);
		JLabel Label = new JLabel("Encrypt Homework or Decrypt Submission");

		JButton encryptButton = new JButton("Encrypt");
		encryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the encryption by creating new encrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame inputFrame = new JFrame("Madlibs Input");
				
				Box inputBox = new Box(BoxLayout.Y_AXIS);
				
				try {

					InputPanel madlibsPanel = new InputPanel(labels, fields);
					inputBox.add(madlibsPanel);
					
					JButton doneButton = new JButton("Done");
					doneButton.addActionListener(new ActionListener() {
						
						@Override
			            public void actionPerformed(ActionEvent e) {

								Box resultBox = new Box(BoxLayout.Y_AXIS);
								JFrame resultFrame = new JFrame("MadLibs Results");
									resultBox.add(panel);
								
								JButton saveButton = new JButton("Save");
								saveButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										try {
											save(madLibsObj);
										} catch (IOException e1) {
											JOptionPane.showMessageDialog(null,
												    "Could not save file.",
												    "Error",
												    JOptionPane.ERROR_MESSAGE);
										}
									}
								});
								resultBox.add(saveButton);
								resultFrame.add(resultBox);
								resultFrame.pack();
								resultFrame.setVisible(true);
								inputFrame.dispose();   
							}
						});
				inputBox.add(doneButton);
				} catch (SAXException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ParserConfigurationException e2) {
					e2.printStackTrace();
				}
				
				inputFrame.add(inputBox);
				inputFrame.pack();
                inputFrame.setVisible(true);
           }
        });
		
		JButton decryptButton = new JButton("Decrypt");
		decryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the decryption by creating new decrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decrypt decryptPanel = new decrypt();
								});
								resultBox.add(saveButton);
								resultFrame.add(resultBox);
								resultFrame.pack();
								resultFrame.setVisible(true);
								inputFrame.dispose();   
							}
						});
				inputBox.add(doneButton);
				} catch (SAXException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ParserConfigurationException e2) {
					e2.printStackTrace();
				}
				
				inputFrame.add(inputBox);
				inputFrame.pack();
                inputFrame.setVisible(true);
           }
        });
		
		initialScreen.add(Label);
		selectBox.add(encryptButton);
		initialScreen.add(selectBox);
		add(initialScreen);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Encryption");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Program());
		frame.setPreferredSize(new Dimension(250,250));
        frame.pack();
        frame.setVisible(true);
		
		
	}
}