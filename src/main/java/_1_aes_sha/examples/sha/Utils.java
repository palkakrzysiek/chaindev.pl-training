package _1_aes_sha.examples.sha;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class Utils {
    public static byte[] concat(byte[] first, byte[] second) {
        int total = first.length + second.length;
        byte[] sum;
        sum = new byte[total];
        System.arraycopy(first, 0, sum, 0, first.length);
        System.arraycopy(second, 0, sum, first.length, second.length);
        return sum;
    }
}
