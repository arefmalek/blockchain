import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {

    // we need the previous hash
    // we need the object that holds the data

    public int index;
    private long timestamp; // not sure how this will end up looking

    private String previousHash = "";
    private String data;
    private String hash = "";

    private int nonce; //this is just the number of times it took to generate the hash

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index; // I'll figure this out later
        this.timestamp = timestamp;
        this.previousHash= previousHash;
        this.data = data;
        this.hash = calculateHash(this);
    }
    public int getIndex() {
        return index;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public String getHash() {
       return this.hash;
    }
    
    public String getPreviousHash() {
        return this.previousHash;
    }
    
    public String getData() {
        return this.data;
    }
    
    public String str() {
        return index + timestamp + previousHash + data + nonce;
    }

    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;

            try {
                digest = MessageDigest.getInstance("SHA-256");
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println(e.toString());
                return null;
            }

            // this is why it's secure, the data is entirely hashed based off the previously mined info (nonce)
            String unhashed_block = block.str();
            final byte block_bytes[] = digest.digest(unhashed_block.getBytes());
            final StringBuilder builder = new StringBuilder();

            for (final byte b : block_bytes) {
                String hex = Integer.toHexString(0xff & b); // this just means we the binary value of each byte

                if (hex.length() == 1) builder.append('0');
                
                builder.append(hex);
            }

            return builder.toString();
        }

        return null;
    }

    public static String zeros(int difficulty) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < difficulty; i++)
            sb.append('0');

        return sb.toString();
    }

    public void mineBlock(int difficulty) {
        this.nonce = 0;

        while(!getHash().substring(0, difficulty).equals(zeros(difficulty))) {
            this.nonce++;
            this.hash = calculateHash(this); // by changing nonce we get a new hash
        }
    }

    public String toString() {
        return this.index + "\n" + this.timestamp + "\n" + this.previousHash  + "\n" + this.data + "\n" + this.hash;
    }
}
