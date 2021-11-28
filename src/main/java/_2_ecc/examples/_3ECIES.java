package _2_ecc.examples;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _3ECIES {
    public static void main(String[] args) throws InvalidCipherTextException {
        var plainText = "ala ma kota".getBytes(StandardCharsets.US_ASCII);

        var curve = ECNamedCurveTable.getByName("secp256k1");
        var domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH(), curve.getSeed());
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        gen.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        var alice = gen.generateKeyPair();
        IESEngine aliceEngine = new IESEngine(
                new ECDHBasicAgreement(),
                new KDF2BytesGenerator(new SHA1Digest()),
                new HMac(new SHA3Digest()),
                new BufferedBlockCipher(new SICBlockCipher(new AESEngine())));

        var bob = gen.generateKeyPair();
        IESEngine bobEngine = new IESEngine(
                new ECDHBasicAgreement(),
                new KDF2BytesGenerator(new SHA1Digest()),
                new HMac(new SHA3Digest()),
                new BufferedBlockCipher(new SICBlockCipher(new AESEngine())));

        //additional data for kdf
        byte[] optionalKdfData = new byte[0];
        //additional data for mac
        byte[] optionalMacData = new byte[0];

        byte[] aesIV = new byte[16];
        var random = new SecureRandom();
        random.nextBytes(aesIV);
        CipherParameters cipherParameters = new ParametersWithIV(new IESWithCipherParameters(optionalKdfData, optionalMacData, 256, 256), aesIV);

        aliceEngine.init(true, alice.getPrivate(), bob.getPublic(), cipherParameters);
        bobEngine.init(false, bob.getPrivate(), alice.getPublic(), cipherParameters);

        //encrypt
        byte[] cipherText = aliceEngine.processBlock(plainText, 0, plainText.length);
        System.out.println(Hex.toHexString(cipherText));

        //decrypt
        byte[] decrypted = bobEngine.processBlock(cipherText, 0, cipherText.length);
        System.out.println(new String(decrypted, StandardCharsets.US_ASCII));
    }
}
