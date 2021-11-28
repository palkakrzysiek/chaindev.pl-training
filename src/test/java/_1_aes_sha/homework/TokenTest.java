package _1_aes_sha.homework;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TokenTest {

    String msg = "msg 1";
    String msg2 = "msg 2";
    String secret = "secret 1";
    String secret2 = "secret 2";


    @Test
    public void hmacShouldBeInBase64Format() {
        assertThat(new Token(secret).issue(msg))
            .isEqualTo("y1OiWlQmQICfRlptOskqRhOG90rKFfEZC50mKgOhCk4=");
    }

    @Test
    public void hmacIsDifferentForDifferentSecrets() {
        assertThat(new Token(secret).issue(msg))
            .isNotEqualTo(new Token(secret2).issue(msg));
    }

    @Test
    public void hmacIsDifferentForDifferentMessages() {
        assertThat(new Token(secret).issue(msg))
            .isNotEqualTo(new Token(secret).issue(msg2));
    }

    @Test
    public void hmacIsPositivelyValidatedForTheSameMessage() {
        var token = new Token(secret);
        String hmac = token.issue(msg);
        assertThat(token.isValid(hmac, msg)).isTrue();
    }

    @Test
    public void hmacIsNegativelyValidatedForDifferentMessage() {
        var token = new Token(secret);
        String hmac = token.issue(msg);
        assertThat(token.isValid(hmac, msg2)).isFalse();
    }


}
