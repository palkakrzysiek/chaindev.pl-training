package _2_ecc.examples.ecdsafail;

import java.math.BigInteger;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class ECDSASig {
    public BigInteger r;
    public BigInteger s;

    public ECDSASig(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }
}
