package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _5AESCTR {
    public static void main(String[] args) {
        byte[] plainText = "ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|".getBytes(StandardCharsets.US_ASCII);
        CipherParameters key = _2Pbkdf2.generateKey("ela ma kota", "sól;)");

        SecureRandom random = new SecureRandom();
        byte[] IV = new byte[16];
        random.nextBytes(IV);

        ParametersWithIV keyWithIV = new ParametersWithIV(key, IV);

        SICBlockCipher mode = new SICBlockCipher(new AESEngine());
        BufferedBlockCipher cipher = new BufferedBlockCipher(mode);
        cipher.init(true, keyWithIV);

        //encrypt
        int cipherTextLen = cipher.getOutputSize(plainText.length);
        byte[] cipherText = new byte[cipherTextLen];
        cipher.processBytes(plainText, 0, plainText.length, cipherText, 0);
        //no do final

        System.out.println(Hex.toHexString(cipherText));
    }
}
