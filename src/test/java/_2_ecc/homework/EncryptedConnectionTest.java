package _2_ecc.homework;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.*;

class EncryptedConnectionTest {

    EncryptedConnection encryptedConnection = new EncryptedConnection();

    @Test
    public void encryptedTextIsPreservedAfterDecryption() throws InvalidCipherTextException {

        var secureRandom = new SecureRandom();

        X9ECParameters curve = ECNamedCurveTable.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH(), curve.getSeed());
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        gen.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = gen.generateKeyPair();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        String plainText = "Ala ma kotów sto.";

        EncryptedConnection.EncryptionResult encrypted;
        encrypted = encryptedConnection.encrypt(plainText.getBytes(StandardCharsets.UTF_8), keyPair.getPublic(), iv);

        byte[] decrypted = encryptedConnection.decrypt(encrypted.cipherText, encrypted.ephemeralPublic, keyPair.getPrivate(), iv);

        assertThat(new String(decrypted, StandardCharsets.UTF_8)).isEqualTo(plainText);

    }




}
