package _2_ecc.homework;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
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
import org.bouncycastle.crypto.params.*;

import java.security.SecureRandom;

/**
 *
 Drugie zadanie to napisanie 2 metod, które będą szyfrowały i odszyfrowywały podany tekst używając ECIES

 metoda szyfrująca powinna przyjmować plain text, klucz publiczny odbiorcy i IV dla AESa, możesz dodać więcej parametrów, jeśli będą ci potrzebne

 metoda do odszyfrowania powinna przyjmować klucz prywatny odbiorcy, cipher text i IV dla AESa, możesz dodać więcej parametrów, jeśli będą ci potrzebne

 */

public class EncryptedConnection {

    public static class EncryptionResult {
        public final AsymmetricKeyParameter ephemeralPublic;
        public final byte[] cipherText;

        EncryptionResult(AsymmetricKeyParameter ephemeralPublic, byte[] cipherText) {
            this.ephemeralPublic = ephemeralPublic;
            this.cipherText = cipherText;
        }
    }

    X9ECParameters curve = ECNamedCurveTable.getByName("secp256k1");
    ECDomainParameters domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH(), curve.getSeed());
    byte[] optionalKdfData = new byte[0];
    byte[] optionalMacData = new byte[0];


    public EncryptionResult encrypt(byte[] plaintext, AsymmetricKeyParameter receiverPublicKey, byte[] iv) throws InvalidCipherTextException {
        IESEngine engine = mkEngine();

        CipherParameters cipherParameters = new ParametersWithIV(new IESWithCipherParameters(optionalKdfData, optionalMacData, 256, 256), iv);

        ECKeyPairGenerator gen = new ECKeyPairGenerator();

        gen.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        AsymmetricCipherKeyPair ephemeral = gen.generateKeyPair();

        engine.init(true, ephemeral.getPrivate(), receiverPublicKey, cipherParameters);

        byte[] cipherText = engine.processBlock(plaintext, 0, plaintext.length);

        return new EncryptionResult(ephemeral.getPublic(), cipherText);
    }

    public byte[] decrypt(byte[] ciphertext, AsymmetricKeyParameter ephemeralPublicKey, AsymmetricKeyParameter receiverPrivateKey, byte[] iv) throws InvalidCipherTextException {
        IESEngine engine = mkEngine();

        CipherParameters cipherParameters = new ParametersWithIV(new IESWithCipherParameters(optionalKdfData, optionalMacData, 256, 256), iv);
        engine.init(false, receiverPrivateKey, ephemeralPublicKey, cipherParameters);
        return engine.processBlock(ciphertext, 0, ciphertext.length);
    }

    private IESEngine mkEngine() {
        return new IESEngine(
            new ECDHBasicAgreement(),
            new KDF2BytesGenerator(new SHA1Digest()),
            new HMac(new SHA3Digest()),
            new BufferedBlockCipher(new SICBlockCipher(new AESEngine())));
    }

}
