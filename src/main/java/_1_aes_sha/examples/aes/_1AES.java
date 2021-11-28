package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _1AES {
    public static void main(String[] args) {
        byte[] plainText = "ala ma kota.....".getBytes(StandardCharsets.US_ASCII);

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);

        KeyParameter keyParameter = new KeyParameter(key);

        BlockCipher cipher = new AESEngine();
        cipher.init(true, keyParameter);

        byte[] cipherText = new byte[16];
        byte[] decrypted = new byte[16];

        cipher.processBlock(plainText, 0, cipherText, 0);
        System.out.println(Hex.toHexString(cipherText));

        cipher.init(false, keyParameter);
        cipher.processBlock(cipherText, 0, decrypted, 0);

        System.out.println(new String(plainText, StandardCharsets.US_ASCII));
        System.out.println(new String(decrypted, StandardCharsets.US_ASCII));
    }
}
