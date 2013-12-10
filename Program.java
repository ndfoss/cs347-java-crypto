import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Add the choose file, generate key, and encrypt buttons to the window
	 * add text fields and labels to the window
	 * add labels for the text fields
	 * 
	 */
	
	public Program() {
        JButton fileButton = new JButton("Choose Homework:");
        final JTextField textField = new JTextField();
        textField.setEditable(false);

        final JTextField keyField = new JTextField();
        keyField.setEditable(false);
        
        JButton setKey = new JButton("Generate Key");
        JLabel encryptLabel = new JLabel("Encrypt Submission");
        JLabel decryptLabel = new JLabel("Decrypt Submission");
        JButton encryptButton = new JButton("Encrypt");
        JButton saveKeys = new JButton("Choose Key File:");

        	saveKeys.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser("...\\cs347-java-crypto");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "KEY documents", "key");
				jfc.setFileFilter(filter);
				int returnVal = jfc.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION){
			    	File file = jfc.getSelectedFile();
			    	keyField.setText(file.getName());
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Please Choose a correct KEY file");
			    }
			}
		});
        
/**
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
			        keyGen.initialize(1024);
				    KeyPair key = keyGen.genKeyPair();

				    
				    KeyFactory fact = KeyFactory.getInstance("RSA");
				    RSAPublicKeySpec pub = fact.getKeySpec(key.getPublic(),
				      RSAPublicKeySpec.class);
				    RSAPrivateKeySpec priv = fact.getKeySpec(key.getPrivate(),
				      RSAPrivateKeySpec.class);

				    saveToFile("public.key", pub.getModulus(),
				      pub.getPublicExponent());
				    saveToFile("private.key", priv.getModulus(),
				      priv.getPrivateExponent());

				    JOptionPane.showMessageDialog(null,
				    		"Keys Created. Select the Key File");
				    
			    }catch (NoSuchAlgorithmException e1){
			    	e1.printStackTrace();
			    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
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
						try {
						File plaintextFile = new File(textField.getText());
						String keyFile = keyField.getText();
						ArrayList<BigInteger> list = new ArrayList<BigInteger>();
						
						list = getParameters(keyFile);
						
						BigInteger mod = list.get(0);
						BigInteger exponent = list.get(1);
						RSAPublicKeySpec spec = new RSAPublicKeySpec(mod, exponent);
						KeyFactory factory;
						
						factory = KeyFactory.getInstance("RSA");
					
						PublicKey pub;
						
							pub = factory.generatePublic(spec);
						
							Encrypt encrypt = new Encrypt(plaintextFile, pub);
							encrypt.showFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1){
							e1.printStackTrace();
						} catch (InvalidKeyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidKeySpecException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (NoSuchAlgorithmException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
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
					try {
						File cipherTextFile = new File(textField.getText());
						String keyFile = keyField.getText();
						ArrayList<BigInteger> list = new ArrayList<BigInteger>();
						
						list = getParameters(keyFile);
						
						BigInteger mod = list.get(0);
						BigInteger exponent = list.get(1);
						RSAPrivateKeySpec spec = new RSAPrivateKeySpec(mod, exponent);
						KeyFactory factory;
						
						factory = KeyFactory.getInstance("RSA");
					
						PrivateKey priv;
						
							priv = factory.generatePrivate(spec);
						
							Decrypt decrypt = new Decrypt(cipherTextFile, priv);
							decrypt.showFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1){
							e1.printStackTrace();
						} catch (InvalidKeyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidKeySpecException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (NoSuchAlgorithmException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
				    
					
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
            .addComponent(saveKeys))
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
                .addComponent(saveKeys)
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
	 * This function writes BigIntegers to a file as Strings with the name specified
	 * @param fileName
	 * @param mod
	 * @param exp
	 * @throws IOException
	 */
	private void saveToFile(String fileName,
			  BigInteger mod, BigInteger exp) throws IOException {
			  PrintWriter pw = new PrintWriter(fileName);
			  try {
				String modString = mod.toString();
				String expString = exp.toString();
			    pw.println(modString);
			    pw.println(expString);
			  } catch (Exception e) {
			    throw new IOException("Unexpected error", e);
			  } finally {
			    pw.close();
			  }
	}
	
	/**
	 * This function checks the list of textfields for any that are empty
	 * @param fields
	 * @return
	 */
	private boolean checkFields(List<JTextField> fields) {
		for (JTextField textField : fields) {
			if (textField.getText().equals(null) || textField.getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	private ArrayList<BigInteger> getParameters(String file) throws IOException{
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;

	    while( ( line = reader.readLine() ) != null ) {
	        list.add(new BigInteger(line));
	    }
	    reader.close();

	    return list;    
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
