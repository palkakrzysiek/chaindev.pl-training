package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _6AESGCM {

    public static void main(String[] args) throws InvalidCipherTextException {
        byte[] plainText = "ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|".getBytes(StandardCharsets.US_ASCII);
        byte[] additionalData = "ip:127.0.0.1:8080".getBytes(StandardCharsets.US_ASCII);

        KeyParameter key = _2Pbkdf2.generateKey("ela ma kota", "sól;)");

        SecureRandom random = new SecureRandom();
        byte[] IV = new byte[16];
        random.nextBytes(IV);

        int macSize = 128;
        AEADParameters keyWithParams = new AEADParameters(key, macSize, IV, additionalData);

        GCMBlockCipher cipher = new GCMBlockCipher(new AESEngine());
        cipher.init(true, keyWithParams);

        //encrypt
        byte[] out = new byte[cipher.getOutputSize(plainText.length)];
        int processed = cipher.processBytes(plainText, 0, plainText.length, out, 0);
        cipher.doFinal(out, processed);

        System.out.println(Hex.toHexString(out));
        //GMAC
        System.out.println(Hex.toHexString(cipher.getMac()));

        //decrypt with correct additional data
        cipher.init(false, keyWithParams);
        int textLen = cipher.getOutputSize(out.length);
        byte[] text = new byte[textLen];
        processed = cipher.processBytes(out, 0, out.length, text, 0);
        cipher.doFinal(text, processed);

        System.out.println(new String(text, StandardCharsets.US_ASCII));

        //decrypt with bad mac
        AEADParameters badParameters = new AEADParameters(key, macSize, IV, "fake_ip".getBytes(StandardCharsets.US_ASCII));
        cipher.init(false, badParameters);
        processed = cipher.processBytes(out, 0, out.length, text, 0);
        cipher.doFinal(text, processed);
    }
}
