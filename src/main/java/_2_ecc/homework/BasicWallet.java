package _2_ecc.homework;

import _1_aes_sha.homework.AES256;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

/**
 Praca domowa na ten tydzień to rozwinięcie tego, co było w zeszłym tygodniu
 w poprzednim module było do zrobienia szyfrowanie klucza prywatnego przy pomocy AES

 W tym tygodniu do szyfrowania które już masz dodaj podpisywanie
 tzn. dopisz metodę, która
 przyjmie: klucz prywatny w formie BigInteger i twoją wiadomość
 zwróci: podpis dla tej wiadomości

 Z poprzedniego tygodnia masz szyfrowanie symetryczne klucza prywatnego, więc masz już pierwszą część portfela, możesz zapisać bezpiecznie klucz prywatny
 w tym zadaniu chodzi o dopisanie drugiej części portfela - podpisywania transakcji, kluczem prywatnym

 flow w portfelu jest zwykle taki
 1 zaszyfrowany klucz prywatny
 2 odszyfrowanie klucza prywatnego przy pomocy AES
 3 podpisanie wiadomości (transakcji) kluczem prywatnym

 do implementacji możesz użyć web3j (nowa biblioteka z modułu 2) lub bouncy castle

 Drugie zadanie to napisanie 2 metod, które będą szyfrowały i odszyfrowywały podany tekst używając ECIES

 metoda szyfrująca powinna przyjmować plain text, klucz publiczny odbiorcy i IV dla AESa, możesz dodać więcej parametrów, jeśli będą ci potrzebne

 metoda do odszyfrowania powinna przyjmować klucz prywatny odbiorcy, cipher text i IV dla AESa, możesz dodać więcej parametrów, jeśli będą ci potrzebne
 */

public class BasicWallet {

    private final byte[] encryptedPrivateKey;
    private final byte[] salt = new byte[64];
    private final byte[] iv = new byte[16];
    private final AES256 aes256 = new AES256();
    SecureRandom secureRandom = new SecureRandom();

    public BasicWallet(BigInteger privateKey, String pass) throws GeneralSecurityException {
        secureRandom.nextBytes(salt);
        secureRandom.nextBytes(iv);
        encryptedPrivateKey = aes256.encrypt(privateKey,pass, salt, iv);
    }

    public Sign.SignatureData sign(byte[] msg, String password) throws GeneralSecurityException {
        return aes256.sign(decryptKey(password), msg);
    }

    private BigInteger decryptKey(String password) throws GeneralSecurityException {
        return aes256.decrypt(encryptedPrivateKey, password, salt, iv);
    }

}
