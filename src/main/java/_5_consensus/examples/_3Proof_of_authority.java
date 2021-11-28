package _5_consensus.examples;

import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class _3Proof_of_authority {
    public static void main(String[] args) {
        var curve = ECNamedCurveTable.getByName("secp256k1");
        var domainParams = new ECDomainParameters(curve.getCurve(), curve.getG(), curve.getN(), curve.getH(), curve.getSeed());
        var gen = new ECKeyPairGenerator();
        gen.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        var bcKeyPair = (ECPrivateKeyParameters) gen.generateKeyPair().getPrivate();

        var publicKey = Sign.publicKeyFromPrivate(bcKeyPair.getD());
        var web3KeyPair = new ECKeyPair(bcKeyPair.getD(), publicKey);


        var block = "{block number, parent hash, transactions, gas used, ...}";

        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);
        sha3.update(block.getBytes(StandardCharsets.US_ASCII));
        var blockHash = sha3.digest();

        var proofOfAuthority = Sign.signMessage(blockHash, web3KeyPair, false);
        System.out.println(Hex.toHexString(proofOfAuthority.getR()));
        System.out.println(Hex.toHexString(proofOfAuthority.getS()));
        System.out.println(Hex.toHexString(proofOfAuthority.getV()));
    }
}
