import java.security.MessageDigest;

public class messageDigestHash {

    public static void main(String[] args) {
        try {
            MessageDigest mD = MessageDigest.getInstance("SHA-256");
            String stringToBeHashed = "this is to be hashed";
            mD.update(stringToBeHashed.getBytes());
            String hashedString = new String(mD.digest());
            System.out.println("Hashed string: " + hashedString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
