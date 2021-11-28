package _4_node.homework;

import _3_tx_block_blockchain.homework.Block;
import _3_tx_block_blockchain.homework.Transaction;
import org.hyperledger.besu.ethereum.trie.SimpleMerklePatriciaTrie;
import org.hyperledger.besu.util.bytes.BytesValue;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //add some pending transactions
    private static List<Transaction> memPool = new ArrayList<>();
    //add wallet address with corresponding balance
    private static SimpleMerklePatriciaTrie<BytesValue, BytesValue> mpt;
    //first block in the blockchain
    private static Block genesis;

    //write simple main that in for loop will pick the transactions from mem pool, create block base on that transactions
    //set correct block number and parent hash
    //update mpt accordingly to them
    //print out the blockchain with that new block
    //if you have any issue write on the forum I WILL HELP YOU :)
}
