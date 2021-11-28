package _3_tx_block_blockchain.homework;

import org.web3j.crypto.Sign;

public class Transaction {
    public Integer amount;
    public String to;
    public Sign.SignatureData signature;
    public String txHash;

    public Transaction(Integer amount, String to, Sign.SignatureData signature) {
        //fill all the fields
    }
}
