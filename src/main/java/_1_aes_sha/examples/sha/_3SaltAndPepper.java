package _1_aes_sha.examples.sha;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Base64;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _3SaltAndPepper {
    //brute force nie pomoże ;)
    private final String pepper;

    public _3SaltAndPepper(String pepper) {
        this.pepper = pepper;
    }

    public String hashPasswd(String pass, String salt) {
        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);
        sha3.update((pass + salt + pepper).getBytes());
        String passwdHash = Base64.toBase64String(sha3.digest());
        return salt + "#" + passwdHash;
    }
}
