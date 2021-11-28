package _5_consensus.examples;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.Arrays;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class _1Proof_of_work {
    public static void main(String[] args) {
        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);

        var keepMining = true;
        byte nonce = 0;
        var minDifficulty = 17;

        while (keepMining) {
            var block = "{parent hash, transactions, gas used, ...}";
            sha3.update(block.getBytes(StandardCharsets.US_ASCII));
            var blockHash = sha3.digest();

            var hashForPow = Arrays.append(blockHash, nonce);

            sha3.update(hashForPow);
            var pow = sha3.digest();

            var powAsBits = new BigInteger(pow).abs().toString(2);
            var padded = String.format("%256s", powAsBits).replace(" ", "0");
            System.out.println("PoW as bits " + padded);

            var difficulty = padded.chars().takeWhile(bit -> bit == '0').count();
            if (difficulty >= minDifficulty) {
                keepMining = false;
            } else {
                nonce++;
            }
        }
        System.out.println("nonce " + nonce);
    }
}
