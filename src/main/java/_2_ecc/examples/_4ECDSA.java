package _2_ecc.examples;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Arrays;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _4ECDSA {
    public static void main(String[] args) throws SignatureException {
        var curve = ECNamedCurveTable.getByName("secp256k1");
        var domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH(), curve.getSeed());
        var gen = new ECKeyPairGenerator();
        gen.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        var bcKeyPair = (ECPrivateKeyParameters) gen.generateKeyPair().getPrivate();

        var msg = "ala ma kota".getBytes(StandardCharsets.US_ASCII);

        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);
        sha3.update((msg));
        byte[] msgHash = sha3.digest();

        var publicKey = Sign.publicKeyFromPrivate(bcKeyPair.getD());
        var web3KeyPair = new ECKeyPair(bcKeyPair.getD(), publicKey);

        var signatureData = Sign.signMessage(msgHash, web3KeyPair, false);
        ECDSASignature sig = new ECDSASignature(new BigInteger(1, signatureData.getR()), new BigInteger(1, signatureData.getS()));
        System.out.println(sig.r + " " + sig.s);

        var recoveredKey = Sign.signedMessageHashToKey(msgHash, signatureData);

        System.out.println("initial pub key " + publicKey);
        System.out.println("recovered pub key " + recoveredKey);

        //przykładowe policzenie adresu portfela
        var digest = sha3.digest(publicKey.toByteArray());

        //pierwsze 160bit
        var wallet = Arrays.copyOfRange(digest, 0, 20);
        System.out.println("wallet address 0x" + Hex.toHexString(wallet));

        //łatwa podmiana algorytmu do DS
    }
}
