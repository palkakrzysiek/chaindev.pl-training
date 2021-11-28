package _2_ecc.examples.ecdsafail;

import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

import java.math.BigInteger;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class ECDSAFail {
    public static void main(String[] args) {
        var privateKey = "44020329788893490135623262343737356251704756286890709590024382843043053401949";
        var D = new BigInteger(privateKey);
        var kyePair = SigningService.keyPairFromPrivate(D);

        System.out.println("==============keys==============");
        System.out.println(((ECPrivateKeyParameters) kyePair.getPrivate()).getD());
        System.out.println(((ECPublicKeyParameters) kyePair.getPublic()).getQ());
        System.out.println("==============keys==============");
        System.out.println("\n");
        System.out.println("\n");

        var token = new Token("uuid", 4242424242L);
        var signature = SigningService.sign(token.bytesForSigning(), kyePair.getPrivate());

        System.out.println("r " + signature.r);
        System.out.println("s " + signature.s);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println(token.timestamp + ":" + token.id + ":" + signature.r + ":" + signature.s);
        System.out.println("\n");
        System.out.println("\n");

        var valid = SigningService.verify(token.bytesForSigning(), signature, kyePair.getPublic());
        System.out.println("is valid: " + valid);
    }
}
