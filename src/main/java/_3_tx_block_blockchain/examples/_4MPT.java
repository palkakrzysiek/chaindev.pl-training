package _3_tx_block_blockchain.examples;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.hyperledger.besu.ethereum.trie.SimpleMerklePatriciaTrie;
import org.hyperledger.besu.util.bytes.Bytes32;
import org.hyperledger.besu.util.bytes.BytesValue;

import java.nio.charset.StandardCharsets;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _4MPT {
    public static void main(String[] args) {
        var tree = new SimpleMerklePatriciaTrie<BytesValue, BytesValue>(b -> b);

        var hash = new SHA3.DigestSHA3(256);

        System.out.println("root hash: " + tree.getRootHash());

        var key = "address".getBytes(StandardCharsets.US_ASCII);
        var value = "ala ma kota 32".getBytes(StandardCharsets.US_ASCII);

        tree.put(
                Bytes32.wrap(hash.digest(key)),
                BytesValue.wrap(value));

        System.out.println("root hash: " + tree.getRootHash());
    }
}
