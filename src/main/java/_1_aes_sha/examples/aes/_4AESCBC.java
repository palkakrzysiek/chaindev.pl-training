package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _4AESCBC {
    public static void main(String[] args) throws InvalidCipherTextException {
        byte[] plainText = "ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|".getBytes(StandardCharsets.US_ASCII);
        CipherParameters key = _2Pbkdf2.generateKey("ela ma kota", "sól;)");
        PKCS7Padding padding = new PKCS7Padding();

        SecureRandom random = new SecureRandom();
        byte[] IV = new byte[16];
        random.nextBytes(IV);

        ParametersWithIV keyWithIV = new ParametersWithIV(key, IV);

        CBCBlockCipher mode = new CBCBlockCipher(new AESEngine());
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(mode, padding);
        cipher.init(true, keyWithIV);

        //encrypt
        int cipherTextLen = cipher.getOutputSize(plainText.length);
        byte[] cipherText = new byte[cipherTextLen];
        int processed = cipher.processBytes(plainText, 0, plainText.length, cipherText, 0);
        cipher.doFinal(cipherText, processed);

        System.out.println(Hex.toHexString(cipherText));
    }
}
