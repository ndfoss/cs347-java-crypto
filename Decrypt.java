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


public class Decrypt{
	File file;
/**
*TODO
*This file handles decryption of the plaintext file. When called,
*this class will take in the character stream from the file, decrypt it,
*and prompt the user to save the decrypted file.
*@throws IOException
 * @throws InvalidKeyException 
*
*/
	
	
	/**
	 * The constructor takes the file as a parameter and decrypts the content with private key using RSA 
	 *and writes the content to a new file
	 * 
	 * 
	 * @param file
	 * @param privateKey
	 * @throws IOException
	 */

        public Decrypt(File file, Key privateKey) throws IOException, InvalidKeyException{
        	
        	try {
					Cipher RSACipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
					RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
			    	String everything = getFileContents(file);
			        String decryptedContent = doDecryption(everything, privateKey, RSACipher);
			        File result = writeToFile(decryptedContent);
			        this.file = result;
			     
        	}catch(NoSuchAlgorithmException e){
    			e.printStackTrace();
    		}catch(NoSuchPaddingException e){
    			e.printStackTrace();
    		}
        }//end Decrypt() constructor
        
        /**
         *The function reads the file contents and converts it to a string
         * 
         * @param file
         * @return
         */
        
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
        
        /**
         * decrypts the string using the cipher and private key
         * 
         * @param everything
         * @param privateKey
         * @param RSACipher
         * @return
         */
        
        private String doDecryption(String everything, Key privateKey, Cipher RSACipher){
        	String decryptedContent = new String();
        	try{
	        	byte[] content = everything.getBytes();
		        RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
		        byte[] textdecrypted = RSACipher.doFinal(content);
		        decryptedContent = textdecrypted.toString();
		        
        	} catch(InvalidKeyException e){
    			e.printStackTrace();
    		} catch(IllegalBlockSizeException e){
    			e.printStackTrace();
    		} catch(BadPaddingException e){
    			e.printStackTrace();
    		} 
        	return decryptedContent;
        }//end doDecryption
        
        
        /**
         * Creates a new file containing the string specified as a parameter
         * 
         * @param decryptedContent
         * @return
         */
        private File writeToFile(String decryptedContent){
        	BufferedWriter writer = null;
        	File decryptedFile = new File("DecryptedAssignment.txt");
	        try
	        {
	        	
	        	writer = new BufferedWriter( new FileWriter(decryptedFile));
	            writer.write(decryptedContent);
	            

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
			return decryptedFile;
        }
        
        /**
         * Makes the file open
         */
        
        public void showFile(){
        	try {
				java.awt.Desktop.getDesktop().edit(this.file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
}//end class
