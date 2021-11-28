package _1_aes_sha.examples.sha;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Base64;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _1AlaMaKota {
    public static void main(String[] args) {
        byte[] val1 = "ala ma kota.".getBytes();
        byte[] val2 = "ala ma 2 koty".getBytes();
        byte[] val3 = "ala nie ma kota".getBytes();

        SHA3.DigestSHA3 sha3 = new SHA3.DigestSHA3(256);

        sha3.update((val1));
        byte[] hash1 = sha3.digest();

        sha3.update(Utils.concat(val2, hash1));
        byte[] hash2 = sha3.digest();

        sha3.update(Utils.concat(val3, hash2));
        byte[] hash3 = sha3.digest();

        System.out.println(Base64.toBase64String(hash1));
        System.out.println(Base64.toBase64String(hash2));
        System.out.println(Base64.toBase64String(hash3));
    }
}
