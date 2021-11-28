package _1_aes_sha.homework;

import _1_aes_sha.examples.sha.Utils;
import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Copyright Adam Smolarek chaindev.pl
 */

// jeśli masz pytania napisz na forum
// forum.chaindev.pl

public class Token {
    private String secret = "";

    public Token(String secret) {
        this.secret = secret;
    }

    SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);

    public String issue(String msg) {
        byte[] saltedMsg = Utils.concat(secret.getBytes(StandardCharsets.UTF_8),msg.getBytes(StandardCharsets.UTF_8));
        byte[] hashed = sha3.digest(saltedMsg);
        return Base64.getEncoder().encodeToString(hashed);
    }

    public Boolean isValid(String mac, String msg) {
        return mac.equals(issue(msg));
    }

}
