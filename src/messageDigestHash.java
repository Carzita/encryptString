import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class messageDigestHash {

    public static void main(String[] args) {

        String stringToBeHashed = "this is to be hashed";
        System.out.println("String before hash: " + stringToBeHashed);

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
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
            System.out.println("Hashed string with salt: " + hashedString);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
