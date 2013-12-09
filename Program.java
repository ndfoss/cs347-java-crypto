import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Add the choose file, generate key, and encrypt buttons to the window
	 * add text fields and labels to the window
	 * add labels for the text fields
	 * 
	 */
	
	public Program() {
        JButton fileButton = new JButton("Choose File:");
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        JLabel keyLabel = new JLabel("Enter Key");
        final JTextField keyField = new JTextField();
        
        JButton setKey = new JButton("Generate Key");
        JLabel encryptLabel = new JLabel("Encrypt Submission");
        JLabel decryptLabel = new JLabel("Decrypt Submission");
        JButton encryptButton = new JButton("Encrypt");


/*
 * Allows for the user to choose a file to encrypt
 * If the user does not choose a file, they will be prompted with an error message
 */
        
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
        
        
        final ArrayList<JTextField> fieldList = new ArrayList<JTextField>();
        fieldList.add(textField);
        fieldList.add(keyField);
        
        	encryptButton.addActionListener(new ActionListener() {
        		
				@Override
				public void actionPerformed(ActionEvent e) {
					if(checkFields(fieldList)){
						File plaintextFile = new File(textField.getText());
						String stringKey = keyField.getText();
						byte[] encodedKey;
						try {
							com.sun.org.apache.xml.internal.security.Init.init();
							encodedKey = Base64.decode(stringKey);
							SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "RSA"); //EDIT: missing 'new'
							Encrypt encrypt = new Encrypt(plaintextFile, originalKey);
							encrypt.showFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1){
							e1.printStackTrace();
						} catch (Base64DecodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    
					}else {
	        			JOptionPane.showMessageDialog(null,
	        					"Choose a File and Enter Security Keys");
	        		}
				}
        		
			});
        
        JButton decryptButton = new JButton("Decrypt");

        decryptButton.addActionListener(new ActionListener() {
			
			//onclick, decrypt the file using the key
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkFields(fieldList)){
				//add decryption functionality
					
				} else {
					JOptionPane.showMessageDialog(null,
        					"Choose a File and Enter Security Keys");
				}
				
			}
		});
 
        /**
         * Created the layout of the window and adds all the buttons, labels, and text fields to it
         */
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(fileButton)
            .addComponent(setKey)
            .addComponent(keyLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textField)
                .addComponent(keyField)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(encryptLabel)
                        .addComponent(encryptButton))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    	.addComponent(decryptLabel)
                        .addComponent(decryptButton))))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, encryptButton, decryptButton);
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(fileButton)
                .addComponent(textField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(setKey))    
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(keyLabel)
                .addComponent(keyField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(encryptLabel)
                        .addComponent(decryptLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(encryptButton)
                        .addComponent(decryptButton)))
                .addComponent(decryptButton))
        );
 
        setTitle("Secure Assignment Manager");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
/**
 * Checks to see if any of the textfields are blank
 */
	
	private boolean checkFields(List<JTextField> fields) {
		for (JTextField textField : fields) {
			if (textField.getText().equals(null) || textField.getText().equals("")) {
				return false;
			}
		}
		return true;
	}

     
	/**
	 * Changes how the window looks
	 * 
	 */
	
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                            
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new Program().setVisible(true);
            }
        });
    }
}