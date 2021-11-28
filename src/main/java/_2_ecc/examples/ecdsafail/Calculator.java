package _2_ecc.examples.ecdsafail;

import org.bouncycastle.crypto.signers.DSAKCalculator;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class Calculator implements DSAKCalculator {

    @Override
    public boolean isDeterministic() {
        return true;
    }

    @Override
    public void init(BigInteger n, SecureRandom random) {

    }

    @Override
    public void init(BigInteger n, BigInteger d, byte[] message) {

    }

    @Override
    public BigInteger nextK() {
        //roll of the dice gave 4 ;)
        return BigInteger.valueOf(4);
    }

}