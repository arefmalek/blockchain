import java.util.ArrayList;

public class Blockchain {
    
    private int difficulty;
    private ArrayList<Block> blocks;

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        blocks = new ArrayList<Block>(0);

        Block genesis = new Block(0, System.currentTimeMillis(), null, "Genesis");
        genesis.mineBlock(difficulty);
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    // TODO: creating and mining new blocks
    public Block lastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public Block newBlock(String data) {
        Block last = lastBlock();
        return new Block(last.index + 1, System.currentTimeMillis(), last.getHash(), data);
    }

    public void addBlock(Block b) {
        if (b != null) {
            b.mineBlock(difficulty); // should be the same as the one here
            blocks.add(b);
        }
    }


    private Boolean validateGenesis() {
        Block first = blocks.get(0);

        if (first.getIndex() != 0) 
            return false;

        if (first.getPreviousHash() != null)
            return false;

        if (first.getHash().equals(Block.calculateHash(first)))
            return true;

        return false;

    }

    private Boolean validateNewBlock(Block newBlock, Block previousBlock) {
        if (newBlock != null && previousBlock != null) {
            if (newBlock.getIndex() != previousBlock.getIndex() + 1)
                return false;

            // we just checking previous block to make sure that this node is
            // authentic
            if (newBlock.getPreviousHash() == null || 
                    !newBlock.getPreviousHash().equals(previousBlock.getHash()))
                return false;

            // checking again that the nonce found is right
            if (newBlock.getHash() == null || 
                    !newBlock.getHash().equals(Block.calculateHash(newBlock)))
                return false;

            return true;
        }

        return false;
    }

    public Boolean validateChain() {
        if (!validateGenesis())
            return false; // how you fail the first block

        for (int i = 1; i < blocks.size(); i++) {
            Block curr = blocks.get(i);
            Block prev = blocks.get(i-1);

            if (!validateNewBlock(curr, prev))
                return false;
        }

        return true;
    }
}
