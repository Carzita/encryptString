import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class messageDigestHash {

    public static void main(String[] args) {

        String stringToBeHashed = "password";
        System.out.println("String before hash: " + stringToBeHashed);

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        System.out.println("salt: " + new String(salt, StandardCharsets.ISO_8859_1));
/*        try (FileOutputStream stream = new FileOutputStream("salt.txt")) {
            stream.write(salt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String encoded = Base64.getEncoder().encodeToString(salt);
        System.out.println("salt hashed and then encoded: " + encoded);
        try {
/*            MessageDigest mD = MessageDigest.getInstance("SHA-256");
            mD.update(stringToBeHashed.getBytes());
            String hashedString = new String(mD.digest());
            System.out.println("Hashed string: " + hashedString);*/

/*            MessageDigest mD = MessageDigest.getInstance("SHA-256");
            mD.update(salt);
//            String hashedString = new String(mD.digest(stringToBeHashed.getBytes(StandardCharsets.UTF_8)));
            byte[] hashedString = mD.digest(stringToBeHashed.getBytes(StandardCharsets.UTF_8));
            System.out.println("Hashed string with salt: " + hashedString);*/

            KeySpec spec = new PBEKeySpec(stringToBeHashed.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hashedString = factory.generateSecret(spec).getEncoded();
            String password = Base64.getEncoder().encodeToString(hashedString);
            System.out.println("encoded password: " + password);
/*            System.out.println("Hashed string with salt: " + Arrays.toString(hashedString));
            System.out.write(hashedString);*/



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
