package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _3Padding {

    public static void main(String[] args) throws InvalidCipherTextException {
        byte[] plainText = "ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|||||ala ma kota|".getBytes(StandardCharsets.US_ASCII);

        CipherParameters key = _2Pbkdf2.generateKey("ela ma kota", "sól;)");
        PKCS7Padding padding = new PKCS7Padding();

        //ECB
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), padding);
        cipher.init(true, key);

        //encrypt
        int outLen = cipher.getOutputSize(plainText.length);
        byte[] out = new byte[outLen];
        int processed = cipher.processBytes(plainText, 0, plainText.length, out, 0);
        cipher.doFinal(out, processed);

        System.out.println(Hex.toHexString(out));

        //decrypt
        cipher.init(false, key);
        int textLen = cipher.getOutputSize(out.length);
        byte[] text = new byte[textLen];
        processed = cipher.processBytes(out, 0, out.length, text, 0);
        int withoutPadding = processed + cipher.doFinal(text, processed);

        //remove padding
        byte[] noPaddingText = new byte[withoutPadding];
        System.arraycopy(text, 0, noPaddingText, 0, withoutPadding);

        System.out.println(new String(text, StandardCharsets.US_ASCII));
        System.out.println(new String(noPaddingText, StandardCharsets.US_ASCII));
    }
}
