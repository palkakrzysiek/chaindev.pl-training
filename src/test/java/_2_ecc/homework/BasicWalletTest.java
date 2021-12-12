package _2_ecc.homework;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import static org.assertj.core.api.Assertions.*;

class BasicWalletTest {

    @Test
    public void walletsConsistentlySignData() throws GeneralSecurityException {
        String password = "somePassword";
        byte[] msg = "some message to sign".getBytes(StandardCharsets.UTF_8);
        BigInteger pk = BigInteger.valueOf(123456789012345678L);
        BasicWallet wallet1 = new BasicWallet(pk, password);
        BasicWallet wallet2 = new BasicWallet(pk, password);
        assertThat(wallet1.sign(msg, password)).isEqualTo(wallet2.sign(msg, password));
    }

}
