import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
 
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
        
        JLabel keyLabel = new JLabel("Enter Key");
        final JTextField keyField = new JTextField();
        
        JButton setKey = new JButton("Generate Key");
        setKey.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
			    KeyPairGenerator keyGen = null;
			    try { 
			        keyGen = KeyPairGenerator.getInstance("RSA");
			        keyGen.initialize(512);
				    KeyPair key = keyGen.generateKeyPair();
				    PrivateKey pk = key.getPrivate();

					keyField.setText(pk.toString());
					keyField.setEditable(false);
			    }catch (NoSuchAlgorithmException e1){
			    	e1.printStackTrace();
			    }
			}
        });
        
        
        
        JLabel encryptLabel = new JLabel("Encrypt Submission");
        JLabel decryptLabel = new JLabel("Decrypt Submission");
        JButton encryptButton = new JButton("Encrypt");
        	encryptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File plaintextFile = new File(textField.getText());
				String stringKey = keyField.getText();
				byte[] encodedKey;
				try {
					encodedKey = Base64.decode(stringKey);
					SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "RSA"); //EDIT: missing 'new'
					Encrypt encrypt = new Encrypt(plaintextFile, originalKey);
					encrypt.showFile();
				} catch (Base64DecodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
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
        	.addGroup(layout.createParallelGroup(LEADING)
            .addComponent(fileButton)
            .addComponent(setKey)
            .addComponent(keyLabel))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(textField)
                .addComponent(keyField)
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
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(setKey))    
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(keyLabel)
                .addComponent(keyField))
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
 
        setTitle("Secure Assignment Manager");
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