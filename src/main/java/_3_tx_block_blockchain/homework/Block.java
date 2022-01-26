package _3_tx_block_blockchain.homework;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Block {
    public List<Transaction> transactions;
    public String blockHash;
    public String parentHash;

    //add transactions root based on MPT, transactions root is a key,
    //for value you have to translate transaction to bytes

    public Block(List<Transaction> transactions, String parentHash) {
        this.transactions = transactions;
        this.parentHash = parentHash;
        SHA3.DigestSHA3 hash = new SHA3.DigestSHA3(256);
        hash.update(parentHash.getBytes(StandardCharsets.US_ASCII));
        transactions.forEach(transaction -> hash.update(transaction.txHash.getBytes(StandardCharsets.US_ASCII)));
        this.blockHash = hash.toString();
    }
}
