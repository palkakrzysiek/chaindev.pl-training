package _1_aes_sha.homework;

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

    public String issue(String msg) {
        //praca domowa
        return null;
    }

    public Boolean isValid(String mac, String msg) {
        //praca domowa
        return null;
    }

}
