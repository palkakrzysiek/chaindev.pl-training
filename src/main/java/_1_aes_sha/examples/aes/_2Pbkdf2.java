package _1_aes_sha.examples.aes;

import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _2Pbkdf2 {
    public static KeyParameter generateKey(String passwd, String salt) {
        PKCS5S2ParametersGenerator kdf = new PKCS5S2ParametersGenerator();

        //1password pbkdf2 = 100k iterations
        int howSlow = 2048;
        kdf.init(passwd.getBytes(), salt.getBytes(), howSlow);
        int generatedKeySize = 256;
        return (KeyParameter) kdf.generateDerivedMacParameters(generatedKeySize);
    }
}
