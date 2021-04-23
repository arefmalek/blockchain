import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {

    // we need the previous hash
    // we need the object that holds the data

    public int index;
    private long timestamp; // not sure how this will end up looking

    private String previousHash = "";
    // private ArrayList<Transaction> data; // I'll do it this way in the
    // future
    private String data;
    private String currentHash = "";

    private int nonce; // still have no idea what this is

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index; // I'll figure this out later
        this.timestamp = timestamp;
        this.previousHash= previousHash;
        this.data = data;
        this.currentHash = null;
    }
    public int getIndex() {
        return index;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public String getCurrentHash() {
        return this.currentHash;
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

            String unhashed_block = block.toString();
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

    //TODO: finish this up
    public void mineBlock(int difficulty) {
        this.nonce = 0;

        while(!getCurrentHash().substring(0, difficulty).equals(zeros(difficulty))) {
            this.nonce++;
            this.currentHash = calculateHash(this); // by changing nonce we get a new hash
        }
    }

    public String toString() {
        return this.index + "\n" + this.timestamp + "\n" + this.previousHash  + "\n" + this.data + "\n" + this.currentHash;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}
