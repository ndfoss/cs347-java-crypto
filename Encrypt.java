import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Encrypt extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = -4065927681564116356L;

/**
*TODO
*This file handles encryption of the plaintext file. When called,
*this class will open a window with a file chooser and a field
*to take the user's key and will encrypt the file when the user 
*hits submit. The encrypted contents will be written to a new file
*
*/

        public Encrypt(){
			Box inputBox = new Box(BoxLayout.Y_AXIS);
			
			try {

				JPanel encryptForm = new JPanel(new GridLayout());
				JLabel label = new JLabel("Enter Private Key");
				JTextField textField = new JTextField();
				final JTextField fileName = new JTextField();
				fileName.setEditable(false);
				JButton choose = new JButton("Choose File");
				choose.addActionListener(new ActionListener() {
					
					@Override
		            public void actionPerformed(ActionEvent e) {
						JFileChooser jfc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
						        "TXT documents", "txt");
						jfc.setFileFilter(filter);
						int returnVal = jfc.showOpenDialog(getParent());
					    if(returnVal == JFileChooser.APPROVE_OPTION){
					    	File file = jfc.getSelectedFile();
					    	fileName.setText(file.getName());
					    }
					    else {
					    	JOptionPane.showMessageDialog(null, "Please Choose a correct TXT file");
					    }
					}
				});
				

				JButton encryptButton = new JButton("Encrypt");
				encryptButton.addActionListener(new ActionListener() {
					
					@Override
		            public void actionPerformed(ActionEvent e) {
						//Encrypt file and open it
							
					}
				}); //end encryptButton actionListener
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
		            public void actionPerformed(ActionEvent e) {
						//Close window and go back to home screen
							
					}
				}); //end cancelButton actionListener
				
				encryptForm.add(choose);
				encryptForm.add(label);
			    encryptForm.add(textField);
			    encryptForm.add(fileName);
			    encryptForm.add(encryptButton);
			    encryptForm.add(cancelButton);
				inputBox.add(encryptForm);
				add(inputBox);
			} //end try
			catch(Exception e){
				e.printStackTrace();
			} //end catch()    
        }//end Encrypt() constructor
        
}//end class