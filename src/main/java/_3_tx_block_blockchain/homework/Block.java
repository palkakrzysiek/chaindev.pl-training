package _3_tx_block_blockchain.homework;

import java.util.List;

public class Block {
    public List<Transaction> transactions;
    public String blockHash;
    public String parentHash;
    //add transactions root based on MPT, transactions root is a key,
    //for value you have to translate transaction to bytes

    public Block(List<Transaction> transactions, String parentHash) {
        //fill all the values
    }
}
