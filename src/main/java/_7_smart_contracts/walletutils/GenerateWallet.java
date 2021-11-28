package _7_smart_contracts.walletutils;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class GenerateWallet {
    //this will help you generate new wallet file
    public static void main(String[] args) throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException, InterruptedException {
        var walletFile = WalletUtils.generateNewWalletFile(Password.pass, new File("src/main/resources/"));
        var wallet = WalletUtils.loadCredentials(Password.pass, "src/main/resources/" + walletFile);
        System.out.println(wallet.getAddress());
    }
}
