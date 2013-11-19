import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;
 
public class Program extends JFrame {
    public Program() {
        JButton fileButton = new JButton("Choose File:");;
        JTextField textField = new JTextField();
        JLabel encryptLabel = new JLabel("Encrypt Submission");
        JLabel decryptLabel = new JLabel("Decrypt Submission");
        JButton encryptButton = new JButton("Encrypt");
        	encryptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame encryptFrame = new JFrame("Encrypt Homework File");
				encryptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				encryptFrame.getContentPane().add(new Encrypt());
				encryptFrame.setPreferredSize(new Dimension(500,500));
		        encryptFrame.pack();
		        encryptFrame.setVisible(true);
			}
		});
        
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
			
			//on submit, open the decryption by creating new encrypt() object
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame decryptFrame = new JFrame("Decrypt File Submission");
				decryptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				decryptFrame.getContentPane().add(new Decrypt());
				decryptFrame.setPreferredSize(new Dimension(500,500));
		        decryptFrame.pack();
		        decryptFrame.setVisible(true);
				
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