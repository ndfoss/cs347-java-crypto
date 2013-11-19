import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Encrypt{
	File file;
/**
*TODO
*This file handles encryption of the plaintext file. When called,
*this class will take in the character stream from the file, encrypt it,
*and prompt the user to save the encrypted file.
*@throws IOException
*
*/

        public Encrypt(File file, Key privateKey) throws IOException{
        	
        	try {
					Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			    	String everything = getFileContents(file);
			        String encryptedContent = doEncryption(everything, privateKey, rsaCipher);
			        File result = writeToFile(encryptedContent);
			        this.file = result;
			     
        	}catch(NoSuchAlgorithmException e){
    			e.printStackTrace();
    		}catch(NoSuchPaddingException e){
    			e.printStackTrace();
    		}
        }//end Encrypt() constructor
        
        private String getFileContents(File file){
        	BufferedReader br;
        	String everything = new String();
			try {
				br = new BufferedReader(new FileReader(file));
	        	try {
			        StringBuilder sb = new StringBuilder();
			        String line = br.readLine();
	
			        while (line != null) {
			            sb.append(line);
			            sb.append("\n\n");
			            line = br.readLine();
			        }
			        everything = sb.toString();
	        	} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
			        br.close();
			    }
		    }catch (IOException e){
		    	e.printStackTrace();
		    }
        	return everything;
        }//end getFileContents
        
        private String doEncryption(String everything, Key privateKey, Cipher rsaCipher){
        	String encryptedContent = new String();
        	try{
	        	byte[] content = everything.getBytes();
		        rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey);
		        byte[] textEncrypted = rsaCipher.doFinal(content);
		        encryptedContent = textEncrypted.toString();
		        
        	} catch(InvalidKeyException e){
    			e.printStackTrace();
    		} catch(IllegalBlockSizeException e){
    			e.printStackTrace();
    		} catch(BadPaddingException e){
    			e.printStackTrace();
    		} 
        	return encryptedContent;
        }//end doEncryption
        
        private File writeToFile(String encryptedContent){
        	BufferedWriter writer = null;
        	File encryptedFile = new File("EncryptedAssignment.txt");
	        try
	        {
	        	
	        	writer = new BufferedWriter( new FileWriter(encryptedFile));
	            writer.write(encryptedContent);
	            

	        }
	        catch ( IOException e)
	        {
	        	e.printStackTrace();
	        }
	        finally
	        {
	            try
	            {
	                if ( writer != null)
	                writer.close( );
	            }
	            catch ( IOException e)
	            {
	            	e.printStackTrace();
	            }
	        }
			return encryptedFile;
        }
        
        public void showFile(){
        	try {
				java.awt.Desktop.getDesktop().edit(this.file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
}//end class