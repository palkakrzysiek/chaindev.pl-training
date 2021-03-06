package _1_aes_sha.homework;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.KeySpec;

/**
 * Copyright Adam Smolarek chaindev.pl
 */

// jeśli masz pytania napisz na forum
// forum.chaindev.pl

public class AES256 {

    public AES256() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }


    public byte[] encrypt(BigInteger privateKey, String pass, byte[] salt, byte[] iv) throws GeneralSecurityException {
        var msg = privateKey.toByteArray();
        return aesEnDeCrypt(Cipher.ENCRYPT_MODE, msg, pass, salt, iv);
    }

    public BigInteger decrypt(byte[] cipherText, String pass, byte[] salt, byte[] iv) throws GeneralSecurityException {
        var msg = aesEnDeCrypt(Cipher.DECRYPT_MODE, cipherText, pass, salt, iv);
        return new BigInteger(msg);
    }

    public Sign.SignatureData sign(BigInteger privateKey, byte[] msg) throws GeneralSecurityException {
        ECKeyPair keyPair = ECKeyPair.create(privateKey.toByteArray());
        return Sign.signMessage(msg, keyPair, true);
    }

    private static byte[] aesEnDeCrypt(int mode, byte[] message, String pass, byte[] salt, byte[] iv) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS7Padding", BouncyCastleProvider.PROVIDER_NAME);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
            .getEncoded(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(mode, secret, ivSpec);
        return cipher.doFinal(message);
    }
}
