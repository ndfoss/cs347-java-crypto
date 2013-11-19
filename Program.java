import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class Program extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7064260807681369346L;

	public Program() {
        JButton fileButton = new JButton("Choose File:");
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        
        fileButton.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "TXT documents", "txt");
				jfc.setFileFilter(filter);
				int returnVal = jfc.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	File file = jfc.getSelectedFile();
			    	textField.setText(file.getName());
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Please Choose a correct TXT file");
			    }
			}
		});
        
        JLabel keyLabel = new JLabel("Enter Encryption Key");
        JTextField enterKey = new JTextField();
        
        JLabel encryptLabel = new JLabel("Encrypt Submission");
        JLabel decryptLabel = new JLabel("Decrypt Submission");
        JButton encryptButton = new JButton("Encrypt");
        	encryptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//add encryption functionality
			}
		});
        
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the decryption by creating new encrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//add decryption functionality
				
			}
		});
 
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(fileButton)
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(textField)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(encryptLabel)
                        .addComponent(encryptButton))
                    .addGroup(layout.createParallelGroup(LEADING)
                    	.addComponent(decryptLabel)
                        .addComponent(decryptButton))))
            .addGroup(layout.createParallelGroup(LEADING))
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, encryptButton, decryptButton);
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(fileButton)
                .addComponent(textField))
            .addGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(encryptLabel)
                        .addComponent(decryptLabel))
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(encryptButton)
                        .addComponent(decryptButton)))
                .addComponent(decryptButton))
        );
 
        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new Program().setVisible(true);
            }
        });
    }
}