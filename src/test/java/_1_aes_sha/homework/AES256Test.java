package _1_aes_sha.homework;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import static org.assertj.core.api.Assertions.*;

class AES256Test {

    String pass = "Ala ma kota";
    String salt = "Sól";
    AES256 aes256 = new AES256();
    byte[] iv = "1234567890123456".getBytes(StandardCharsets.US_ASCII);


    @Test
    public void encryptionTest() throws GeneralSecurityException {
        byte[] encrypted = aes256.encrypt(BigInteger.valueOf(1234567890L), pass, salt, iv);

        assertThat(Base64.getEncoder().encodeToString(encrypted)).isEqualTo("s/6M94H5OXxooXVB91oZPA==");
    }


    @Test
    public void decryptionTest() throws GeneralSecurityException {
        BigInteger decrypted = aes256.decrypt(Base64.getDecoder().decode("s/6M94H5OXxooXVB91oZPA=="), pass, salt, iv);

        assertThat(decrypted).isEqualTo(BigInteger.valueOf(1234567890L));
    }

}
