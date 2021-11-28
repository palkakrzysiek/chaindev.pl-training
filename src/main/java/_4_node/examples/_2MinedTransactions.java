package _4_node.examples;

import io.reactivex.disposables.Disposable;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

/**
 * Copyright Adam Smolarek chaindev.pl
 */
public class _2MinedTransactions {
    public static void main(String[] args) throws Exception {
        //use api key
        var web3j = Web3j.build(new HttpService("https://eth-mainnet.alchemyapi.io/v2/get-api-ke-from-peovider"));

        var transactionFlowable = web3j.transactionFlowable();
        Disposable disposable = transactionFlowable.forEach(ethTx -> {
            System.out.println("mined transactions gas price gwei: " + (ethTx.getGasPrice().divide(BigInteger.valueOf(1000000000))));
        });
    }
}
