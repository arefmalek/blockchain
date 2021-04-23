import java.util.List;

public class Blockchain {
    
    private int difficulty;
    private List<Block> blocks;

    public Block lastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public Block newBlock(String data) {
        Block last = lastBlock();
        return new Block(last.index + 1, System.currentTimeMillis(), last.getCurrentHash(), data);
    }

    public Block genesisBlock(String data) {
        return new Block(0, System.currentTimeMillis(), null, data);
    }
}
