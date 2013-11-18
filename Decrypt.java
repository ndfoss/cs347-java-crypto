import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Decrypt extends JPanel{
/**
	 * 
	 */
	private static final long serialVersionUID = -9127033735840882870L;

/**
*TODO
*This class handles decryption of a file. When called, it should
*open a window allowing the user to choose a file and enter a key
*and decrypt the file. This will then open and display the plaintext
*content for the user to view
*
*/

        public Decrypt(){

			Box inputBox = new Box(BoxLayout.Y_AXIS);
			
			try {

				JPanel decryptForm = new JPanel(new GridLayout());
				JFileChooser jfc = new JFileChooser();
				JTextField textField = new JTextField();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "TXT documents", "txt");
				jfc.setFileFilter(filter);
				int returnVal = jfc.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	File file = jfc.getSelectedFile();
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Please Choose a correct TXT file");
			    }

				JButton decryptButton = new JButton("Decrypt");
				decryptButton.addActionListener(new ActionListener() {
					
					@Override
		            public void actionPerformed(ActionEvent e) {
						//decrypt file and open it
							
				}
			}); //end decryptButton actionListener
				
				decryptForm.add(jfc);
			    decryptForm.add(textField);
			    decryptForm.add(decryptButton);
				inputBox.add(decryptForm);
				add(inputBox);
        } //end try
		catch(Exception e){
		    e.printStackTrace();
	    } //end catch()
     }//end Decrypt()
  } //end class