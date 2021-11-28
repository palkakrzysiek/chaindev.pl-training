package _2_ecc.examples;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _2ECDH {
    public static void main(String[] args) {
        var curve = ECNamedCurveTable.getByName("secp256k1");
        var domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH());
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        gen.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        var mul = curve.getCurve().getMultiplier();

        var alice = gen.generateKeyPair();

        var alicePub = (ECPublicKeyParameters) alice.getPublic();
        var alicePrv = (ECPrivateKeyParameters) alice.getPrivate();

        var bob = gen.generateKeyPair();

        var bobPub = (ECPublicKeyParameters) bob.getPublic();
        var bobPrv = (ECPrivateKeyParameters) bob.getPrivate();

        //normalize - to get correct point in 2D
        var aliceShared = mul.multiply(bobPub.getQ(), alicePrv.getD()).normalize();
        var bobSnared = mul.multiply(alicePub.getQ(), bobPrv.getD()).normalize();

        //was it able to establish shared key?
        System.out.println(aliceShared.equals(bobSnared));
    }
}
