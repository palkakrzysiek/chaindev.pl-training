package _2_ecc.examples.ecdsafail;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class SigningService {
    //bitcoin curve
    static final X9ECParameters curveParams = SECNamedCurves.getByName("secp256k1");
    static final ECDomainParameters curve = new ECDomainParameters(
            curveParams.getCurve(),
            curveParams.getG(),
            curveParams.getN(),
            curveParams.getH()
    );

    public static ECDSASig sign(byte[] message,
                                AsymmetricKeyParameter privateKey) {
        //generate nonce
        var kCalculator = new Calculator();

        var signer = new ECDSASigner(kCalculator);
        signer.init(true, privateKey);
        var components = signer.generateSignature(message);
        var r = components[0];
        var s = components[1];

        return new ECDSASig(r, s);
    }

    public static Boolean verify(byte[] message,
                                 ECDSASig signature,
                                 AsymmetricKeyParameter publicKey) {
        var signer = new ECDSASigner();
        signer.init(false, publicKey);
        return signer.verifySignature(message, signature.r, signature.s);
    }

    public static AsymmetricCipherKeyPair generateKeyPair() {
        var secureRandom = new SecureRandom();
        var generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(curve, secureRandom));
        return generator.generateKeyPair();
    }

    public static AsymmetricCipherKeyPair keyPairFromPrivate(BigInteger D) {
        var publicKey = curve.getG().multiply(D).normalize();
        return new AsymmetricCipherKeyPair(
                new ECPublicKeyParameters(publicKey, curve),
                new ECPrivateKeyParameters(D, curve)
        );
    }
}
