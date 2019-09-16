import javax.crypto.*;
import java.util.Arrays;

public class encryptStringMain {

    public static void main(String[] args) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecretKey aesKey = keygen.generateKey();

            Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);

            // Our cleartext
            String text = "This is a test";
            byte[] cleartext = text.getBytes();

            System.out.println("Text: " + text);
            System.out.println("Text byteformat: " + cleartext);
            System.out.println("Text byteformat with Arrays.toString: " + Arrays.toString(cleartext));

            // Encrypt the cleartext
            byte[] ciphertext = aesCipher.doFinal(cleartext);

            System.out.println("Encryptet byteformat: " + ciphertext);

            // Initialize the same cipher for decryption
            aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

            // Decrypt the ciphertext
            byte[] cleartext1 = aesCipher.doFinal(ciphertext);

            String s = new String(cleartext1, "UTF-8");
            System.out.println("Decrypted byteformat: " + cleartext1);
            System.out.println("Decrypted byteformat to string: " + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
