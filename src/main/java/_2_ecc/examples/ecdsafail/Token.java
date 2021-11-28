package _2_ecc.examples.ecdsafail;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.charset.StandardCharsets;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class Token {
    public final String id;
    public final Long timestamp;

    public Token(String id, Long timestamp) {
        this.timestamp = timestamp;
        this.id = id;
    }

    public byte[] bytesForSigning() {
        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);
        return sha3.digest((timestamp + ":" + id).getBytes(StandardCharsets.UTF_8));
    }
}
