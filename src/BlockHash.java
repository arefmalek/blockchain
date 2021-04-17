import java.security.MessageDigest;

public class BlockHash {
    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;

            digest = MessageDigest.getInstance("SHA-256");
           
            try {
                digest = MessageDigest.getInstance("SHA-256");
            }
            catch (NoSuchAlgorithmException e) {
                return null;
            }
        }

        return null;
    }
}
