public class Transaction {

    // owner's public key
    // hash
    // owner's signature
    private String sender;
    private String recipient;
    private int amount; 

    public Transaction(String sender, String recipient, int amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }


}
