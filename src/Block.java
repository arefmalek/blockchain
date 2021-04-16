public class Block {

    // we need the previous hash
    // we need the object that holds the data

    private int index;
    private long timestamp; // not sure how this will end up looking
    private String previous_hash = "";
    private Transaction transaction;
    private String current_hash = "";

    private int nonce; // still have no idea what this is

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
