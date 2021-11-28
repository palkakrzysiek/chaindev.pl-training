package _1_aes_sha.examples.sha;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Base64;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _2Token {
    private final String secret;

    public _2Token(String secret) {
        this.secret = secret;
    }

    public String issue(String msg) {
        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);
        sha3.update((msg + secret).getBytes());
        String hash = Base64.toBase64String(sha3.digest());
        return msg + "#" + hash;
    }

    public Boolean isValid(String token, String mac) {
        //praca domowa
        return null;
    }

}
